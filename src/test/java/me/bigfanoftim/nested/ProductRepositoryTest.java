package me.bigfanoftim.nested;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("ProductRepository 인터페이스")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Nested
    @DisplayName("findAll 메소드")
    class FindAll {

        @Nested
        @DisplayName("제품이 존재하면")
        class WithProduct {

            @BeforeEach
            void prepare() {
                Product product1 = Product.builder()
                        .name("신발")
                        .price(80000)
                        .stock(3)
                        .build();
                productRepository.save(product1);
            }

            @Test
            @DisplayName("존재하는 모든 제품을 리턴한다.")
            public void shouldReturn_AllProducts() throws Exception {
                List<Product> all = productRepository.findAll();

                assertThat(all.size()).isEqualTo(1);
            }
        }

        @Nested
        @DisplayName("제품이 존재하지 않으면")
        class WithoutProduct {

            @Test
            @DisplayName("빈 리스트를 리턴한다.")
            public void should() throws Exception {
                List<Product> all = productRepository.findAll();

                assertThat(all.size()).isEqualTo(0);
            }
        }
    }
}