INSERT INTO image (id, name, path)
VALUES (1, '335125097_223158730281960_5100082204035434949_n.jpg',
        'shop-app/src/main/resources/images/335125097_223158730281960_5100082204035434949_n.jpg'),
       (2, 'tema 11+12 pag 1.jpg', 'shop-app/src/main/resources/images/tema 11+12 pag 1.jpg'),
       (3, 'tema 11+12 pag 2.jpg', 'shop-app/src/main/resources/images/tema 11+12 pag 2.jpg'),
       (4, 'tema 11+12 pag 3.jpg', 'shop-app/src/main/resources/images/tema 11+12 pag 3.jpg'),
       (5, '335573427_155272630390059_7590884133337108626_n.jpg',
        'shop-app/src/main/resources/images/335573427_155272630390059_7590884133337108626_n.jpg'),
       (6, '313878686_519354953126411_4822283254495272838_n.jpg',
        'shop-app/src/main/resources/images/313878686_519354953126411_4822283254495272838_n.jpg'),
       (7, '313891400_700646631104816_9172045020890675311_n.jpg',
        'shop-app/src/main/resources/images/313891400_700646631104816_9172045020890675311_n.jpg'),
       (8, '314570797_1546778885763786_2156627457179065441_n.jpg',
        'shop-app/src/main/resources/images/314570797_1546778885763786_2156627457179065441_n.jpg'),
       (9, '314924370_3409181105996202_7999375419498965161_n.jpg',
        'shop-app/src/main/resources/images/314924370_3409181105996202_7999375419498965161_n.jpg'),
       (10, '314663361_5262535420522593_4315686798773457756_n.jpg',
        'shop-app/src/main/resources/images/314663361_5262535420522593_4315686798773457756_n.jpg');

INSERT INTO product(id, name, price, quantity)
VALUES (1, 'PRODUCT_1', 100, 100),
       (2, 'PRODUCT_2', 110, 32),
       (3, 'PRODUCT_3', 105, 12),
       (5, 'PRODUCT_04', 1000, 12);

INSERT INTO product_images(product_id, images_id)
VALUES (1, 1),
       (2, 3),
       (3, 4),
       (5, 6),
       (5, 7),
       (5, 8),
       (5, 9),
       (5, 10);