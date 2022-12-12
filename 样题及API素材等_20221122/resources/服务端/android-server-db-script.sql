-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 124.222.53.3    Database: android-server
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `at_order`
--

DROP TABLE IF EXISTS `at_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `at_order` (
  `id` bigint NOT NULL COMMENT '商品主键',
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `total_price` double DEFAULT NULL COMMENT '订单总价',
  `reduced_price` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `version` int DEFAULT NULL,
  `valid` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `at_order`
--

LOCK TABLES `at_order` WRITE;
/*!40000 ALTER TABLE `at_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `at_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `at_order_detail`
--

DROP TABLE IF EXISTS `at_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `at_order_detail` (
  `id` bigint NOT NULL COMMENT '商品主键',
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_num` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `version` int DEFAULT NULL,
  `valid` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `at_order_detail`
--

LOCK TABLES `at_order_detail` WRITE;
/*!40000 ALTER TABLE `at_order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `at_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `at_product`
--

DROP TABLE IF EXISTS `at_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `at_product` (
  `id` bigint NOT NULL COMMENT '商品主键',
  `product_name` varchar(45) DEFAULT NULL COMMENT '商品名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '商品描述',
  `price` double DEFAULT NULL COMMENT '商品价格',
  `pic` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `index_type` tinyint DEFAULT '0' COMMENT '0:其它 1:新品推荐  2:最近流行',
  `create_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `version` int DEFAULT NULL,
  `valid` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `at_product`
--

LOCK TABLES `at_product` WRITE;
/*!40000 ALTER TABLE `at_product` DISABLE KEYS */;
INSERT INTO `at_product` VALUES (1594262076572884994,'晨光会议笔','手绘中性笔黑色0.5',15.9,'./images/pen.jpg',1,'2022-11-20 17:31:14',NULL,NULL,1),(1594266554533486594,'复古怀旧铁艺飞机模型','模型摆件战斗机家居客厅手工装饰品',199.9,'./images/plane.jpg',1,'2022-11-20 17:49:01',NULL,NULL,1),(1594267619156938754,'维克多利数学作业纸带','作业纸数学小学生信纸草稿纸',7.9,'./images/paper.jpg',1,'2022-11-20 17:53:15',NULL,NULL,1),(1594309638336032770,'布鞋秋季懒人一脚跌蹬','老北京布鞋帆布亚麻千层加绒棉鞋',59,'./images/shoe.jpg',1,'2022-11-20 20:40:13',NULL,NULL,1),(1594309638952595457,'联想小新笔记本16英寸','联想小新Pro16锐龙版笔记本电脑',59,'./images/lenovo.jpg',1,'2022-11-20 20:40:13',NULL,NULL,1),(1594309639309111298,'戴尔显示器27英寸','戴尔显示器27英寸酷睿i7/i9高配一体机',5389,'./images/dell.jpg',2,'2022-11-20 20:40:14',NULL,NULL,1),(1594309639665627137,'书桌垫学生学习写字台','办公PU皮革防水防油书桌垫',19,'./images/table_mat.jpg',2,'2022-11-20 20:40:14',NULL,NULL,1),(1594309640085057538,'椅子坐垫','办公椅子记忆棉坐垫',20.8,'./images/cushion.jpg',1,'2022-11-20 20:40:14',NULL,NULL,1),(1594309640412213249,'康奈尔笔记本','康奈尔笔记本复习学习考试考研笔记本',80.5,'./images/notebook.jpg',1,'2022-11-20 20:40:14',NULL,NULL,1),(1594309640768729090,'海奥华预言中文版','海奥华预言中文版无删减',19.5,'./images/book.jpg',1,'2022-11-20 20:40:14',NULL,NULL,1),(1594309641192353794,'海奥华预言中文版','海奥华预言中文版无删减',19.5,'./images/book.jpg',1,'2022-11-20 20:40:14',NULL,NULL,1);
/*!40000 ALTER TABLE `at_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `at_user`
--

DROP TABLE IF EXISTS `at_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `at_user` (
  `id` bigint NOT NULL COMMENT '用户主键',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `pwd` varchar(100) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(45) DEFAULT NULL COMMENT '昵称',
  `head_pic` varchar(45) DEFAULT NULL COMMENT '头像图片路径，待定',
  `create_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `version` int DEFAULT NULL,
  `valid` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `at_user`
--

LOCK TABLES `at_user` WRITE;
/*!40000 ALTER TABLE `at_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `at_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-20 23:20:51
