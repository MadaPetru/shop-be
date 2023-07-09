package ro.adi.shop.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ro.adi.shop.products.ProductDataProvider;
import ro.adi.shop.products.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.adi.shop.Constants.EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR;
import static ro.adi.shop.Urls.BASE_URL_PRODUCTS;
import static ro.adi.shop.Urls.URL_FIND_ALL_PRODUCTS_PAGEABLE;

@WebMvcTest(ProductControllerImpl.class)
@TestPropertySource(locations = "classpath:application-test.yaml")
class ProductControllerImplTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ProductService productService;

    @Test
    void findAllProducts_ShouldSucceed() throws Exception {
        var request = ProductDataProvider.createSearchAllPageableRequestMock();

        mockMvc.perform(post(URL_FIND_ALL_PRODUCTS_PAGEABLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(productService, times(1)).findAllProducts(any());
    }

    @Test
    void findAllProducts_ThrowExceptionShouldBeHandled() throws Exception {
        var request = ProductDataProvider.createSearchAllPageableRequestMock();
        doThrow(RuntimeException.class).when(productService).findAllProducts(any());

        var response = mockMvc.perform(post(URL_FIND_ALL_PRODUCTS_PAGEABLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError())
                .andReturn();

        verify(productService, times(1)).findAllProducts(any());
        assertEquals(EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR, response.getResponse().getContentAsString());
    }

    @Test
    void deleteProductById_ShouldSucceed() throws Exception {
        mockMvc.perform(delete(BASE_URL_PRODUCTS + "/{id}", 1))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProductById(1);
    }

    @Test
    void deleteProductById_ThrowExceptionShouldBeHandled() throws Exception {
        doThrow(RuntimeException.class).when(productService).deleteProductById(1);

        var response = mockMvc.perform(delete(BASE_URL_PRODUCTS + "/{id}", 1))
                .andExpect(status().is5xxServerError())
                .andReturn();

        verify(productService, times(1)).deleteProductById(1);
        assertEquals(EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR, response.getResponse().getContentAsString());
    }


    @Test
    void saveProduct_ShouldSucceed() throws Exception {
        var request = ProductDataProvider.createCreateProductRequestDtoMock();

        mockMvc.perform(post(BASE_URL_PRODUCTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(productService, times(1)).saveProduct(any());
    }

    @Test
    void saveProduct_ThrowExceptionShouldBeHandled() throws Exception {
        var request = ProductDataProvider.createCreateProductRequestDtoMock();
        doThrow(RuntimeException.class).when(productService).saveProduct(any());

        var response = mockMvc.perform(post(BASE_URL_PRODUCTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError())
                .andReturn();

        verify(productService, times(1)).saveProduct(any());
        assertEquals(EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR, response.getResponse().getContentAsString());
    }

    @Test
    void updateProduct_ShouldSucceed() throws Exception {
        var request = ProductDataProvider.createUpdateProductRequestDtoMock();

        mockMvc.perform(put(BASE_URL_PRODUCTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(productService, times(1)).updateProduct(any());
    }

    @Test
    void updateProduct_ThrowExceptionShouldBeHandled() throws Exception {
        var request = ProductDataProvider.createUpdateProductRequestDtoMock();
        doThrow(RuntimeException.class).when(productService).updateProduct(any());

        var response = mockMvc.perform(put(BASE_URL_PRODUCTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError())
                .andReturn();

        verify(productService, times(1)).updateProduct(any());
        assertEquals(EXPECTED_MESSAGE_FOR_INTERNAL_APP_ERROR, response.getResponse().getContentAsString());
    }
}