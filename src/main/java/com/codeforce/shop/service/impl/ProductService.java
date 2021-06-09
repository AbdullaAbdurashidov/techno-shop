package com.codeforce.shop.service.impl;

import com.codeforce.shop.domain.*;
import com.codeforce.shop.dto.CartDTO;
import com.codeforce.shop.dto.OrderDto;
import com.codeforce.shop.dto.composite.PagingDto;
import com.codeforce.shop.dto.ProductDTO;
import com.codeforce.shop.dto.composite.ProductTableDto;
import com.codeforce.shop.mapper.CartMapper;
import com.codeforce.shop.mapper.OrderMapper;
import com.codeforce.shop.mapper.ProductMapper;
import com.codeforce.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInventoryRepository inventoryRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HashService idService;

    @Autowired
    private FileService fileService;

    private ProductMapper productMapper;

    private CartMapper cartMapper;

    private OrderMapper orderMapper;

    public ProductService(ProductMapper productMapper,
                          CartMapper cartMapper,
                          OrderMapper orderMapper){
        this.productMapper = productMapper;
        this.cartMapper = cartMapper;
        this.orderMapper = orderMapper;
    }



    public ProductDTO getById(String id){
        return productMapper.toDto(productRepository.getById(id));
    }

    public ProductTableDto getProductTableById(String Id){
        ProductTableDto productTableDto = new ProductTableDto();
        productTableDto.setProductDto(productMapper.toDto(productRepository.getById(Id)));
        productTableDto.setFileDto(fileService.getFileByOwnerHashID(Id));
        return productTableDto;
    }

    public PagingDto getProductPagingDto(int page, int length, String orderColumn, String orderType, String search){
        PagingDto pagingDto = new PagingDto();
        List<ProductDTO> dtoList = findAllProductTableDto();
        pagingDto.setRecordsTotal(dtoList.size());
        pagingDto.setRecordsFiltered(dtoList.size());
        List<ProductDTO> resultList = new ArrayList<>();
        int start = (page-1)*length;
        int end = length-1;
        for( int i=0; i< dtoList.size(); i++){
            if(i>=start && i<=end)
                resultList.add(dtoList.get(i));
        }
        pagingDto.setData(resultList);
        return pagingDto;
    }

    public List<ProductDTO> findAllProductTableDto(){

        List<ProductDTO> productDtoList = productMapper.toDto(productRepository.getAllBy());

        return productDtoList;
    }

    public List<Cart> getCartByUserId(String userId){
        return cartRepository.getByUserIdAndStatus(userId, "cart");
    }

    public List<Cart> getOrderItemsByUserId(String userId){return cartRepository.getByUserIdAndStatus(userId, "order");}

    public ProductCategory getProductCategoryList(){
        ProductCategory categories = categoryRepository.getByParentIdIsNull();
        return categories;
    }

    //Saving
    public ProductDTO saveProduct(ProductDTO productDTO){
        String hashID=idService.getHashID();
        productDTO.setCreatedAt(LocalDate.now());
        productDTO.setId(hashID);
        productRepository.save(productMapper.toEntity(productDTO));
        return productDTO;
    }

    public ProductInventory saveProductInventory(ProductInventory productInventory, String id){
        if(getById(id)!=null){
            ProductInventory result = inventoryRepository.save(productInventory);
            ProductDTO product = getById(id);
            product.setInventoryId(result.getId());
            saveProduct(product);
            return result;
        }else{
            return null;
        }
    }

    public Discount saveDiscount(Discount discount, String id){
        if(getById(id)!=null){
            Discount result= discountRepository.save(discount);
            ProductDTO product = getById(id);
            product.setDiscountId(result.getId());
            saveProduct(product);
            return result;
        }else{
            return null;
        }
    }

    public OrderDto saveOrder(String userId) {
        Order order = new Order();
        order.setTotal(0d);
        List<Cart> cartList = getCartByUserId(userId);
        cartList.stream().forEach( x -> {
                order.setTotal(order.getTotal()+getById(x.getProductId()).getPrice()*x.getQuantity());
                x.setStatus("order");
                cartRepository.save(x);
        });
        order.setUserId(userId);
        order.setPaymentId(0l);
        order.setCreatedAt(LocalDate.now());
        return orderMapper.toDto(orderRepository.save(order));
    }

    public ProductCategory saveCategory(ProductCategory productCategory){
        if(categoryRepository.getById(productCategory.getParentId())!=null){
            categoryRepository.save(productCategory);
            return productCategory;
        }else{
            return null;
        }
    }

    public CartDTO saveCart(CartDTO dto){
        if(userService.getUserById(dto.getUserId())!=null && getById(dto.getProductId())!=null){
            cartRepository.save(cartMapper.toEntity(dto));
            return dto;
        }
        else{
            return null;
        }
    }

    //


}
