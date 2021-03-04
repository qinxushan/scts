-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: scts-tms
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tms_clazz`
--

DROP TABLE IF EXISTS `tms_clazz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_clazz` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `class_id` int NOT NULL COMMENT '班级次序 e.g.(1班、2班)',
  `profession_id` int NOT NULL COMMENT '专业编号',
  `student_size` int DEFAULT NULL COMMENT '班级总人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tms_clazz`
--

LOCK TABLES `tms_clazz` WRITE;
/*!40000 ALTER TABLE `tms_clazz` DISABLE KEYS */;
INSERT INTO `tms_clazz` VALUES (1,1,1,30);
/*!40000 ALTER TABLE `tms_clazz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_college`
--

DROP TABLE IF EXISTS `tms_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_college` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '学院编号',
  `school_id` int NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '学院名称',
  `description` varchar(200) DEFAULT NULL COMMENT '学院描述',
  `order_id` int NOT NULL COMMENT '学院顺序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学院表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tms_college`
--

LOCK TABLES `tms_college` WRITE;
/*!40000 ALTER TABLE `tms_college` DISABLE KEYS */;
INSERT INTO `tms_college` VALUES (1,1,'信息技术学院','信息技术学科',1);
/*!40000 ALTER TABLE `tms_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_course`
--

DROP TABLE IF EXISTS `tms_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int NOT NULL COMMENT '教师编号(用户编号)',
  `name` varchar(100) NOT NULL COMMENT '课程名',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '课程描述',
  `duration` varchar(100) DEFAULT NULL COMMENT '持续周数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tms_course`
--

LOCK TABLES `tms_course` WRITE;
/*!40000 ALTER TABLE `tms_course` DISABLE KEYS */;
INSERT INTO `tms_course` VALUES (1,1,'操作系统','难','1-17'),(2,1,'软件工程','难','1-17'),(3,1,'微积分','难','1-9');
/*!40000 ALTER TABLE `tms_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_course_arrangement`
--

DROP TABLE IF EXISTS `tms_course_arrangement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_course_arrangement` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程安排编号',
  `course_id` int NOT NULL COMMENT '课程编号',
  `teacher_id` int NOT NULL COMMENT '教师编号(用户编号)',
  `class_id` int NOT NULL COMMENT '班级编号',
  `class_room` varchar(20) NOT NULL COMMENT '上课地点',
  `start_week` int NOT NULL COMMENT '启始周次',
  `end_week` int DEFAULT NULL COMMENT '结束周次',
  `day_order` int NOT NULL COMMENT '星期',
  `begin_section` int NOT NULL COMMENT '启始小节',
  `end_section` int NOT NULL COMMENT '结束小节',
  `latitude` decimal(10,6) NOT NULL COMMENT '上课地点纬度',
  `longitude` decimal(10,6) NOT NULL COMMENT '上课地点经度',
  `single_or_double` int DEFAULT NULL COMMENT '单双周(1->单周,2->双周)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程安排表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tms_course_arrangement`
--

LOCK TABLES `tms_course_arrangement` WRITE;
/*!40000 ALTER TABLE `tms_course_arrangement` DISABLE KEYS */;
INSERT INTO `tms_course_arrangement` VALUES (1,1,1,1,'木铎楼601',1,17,2,1,2,111.000000,111.000000,2),(2,1,1,1,'木铎楼507',1,17,4,3,4,111.000000,111.000000,1),(3,2,1,1,'木铎楼504',1,17,4,3,4,111.000000,111.000000,0);
/*!40000 ALTER TABLE `tms_course_arrangement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_course_attendance`
--

DROP TABLE IF EXISTS `tms_course_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_course_attendance` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程考勤编号',
  `student_id` int NOT NULL COMMENT '学生编号',
  `course_arrangment_id` int NOT NULL COMMENT '课程安排编号',
  `state` int NOT NULL COMMENT '打卡状态: 1->签到完成, 2->迟到, 3->病假, 4->事假',
  `gmt_create` datetime(6) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考勤表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tms_course_attendance`
--

LOCK TABLES `tms_course_attendance` WRITE;
/*!40000 ALTER TABLE `tms_course_attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `tms_course_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_profession`
--

DROP TABLE IF EXISTS `tms_profession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_profession` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '专业编号',
  `college_id` int NOT NULL COMMENT '学院编号',
  `name` varchar(50) NOT NULL COMMENT '专业名称',
  `description` varchar(200) DEFAULT NULL COMMENT '专业描述',
  `order_id` int NOT NULL COMMENT '专业顺序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tms_profession`
--

LOCK TABLES `tms_profession` WRITE;
/*!40000 ALTER TABLE `tms_profession` DISABLE KEYS */;
INSERT INTO `tms_profession` VALUES (1,1,'软件工程','程序员',1);
/*!40000 ALTER TABLE `tms_profession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tms_school`
--

DROP TABLE IF EXISTS `tms_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tms_school` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '学校编号',
  `name` varchar(50) NOT NULL COMMENT '学校名称',
  `abbreviation` varchar(20) NOT NULL COMMENT '学校英文缩写',
  `description` varchar(200) DEFAULT NULL COMMENT '学校描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学校表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tms_school`
--

LOCK TABLES `tms_school` WRITE;
/*!40000 ALTER TABLE `tms_school` DISABLE KEYS */;
INSERT INTO `tms_school` VALUES (1,'北京师范大学珠海分校','bnuz','北京师范大学珠海分校（原北京师范大学珠海教育园区）是教育部批准设立、由北京师范大学和珠海市人民政府合作举办、进行本科层次教育的全日制普通高等学校。');
/*!40000 ALTER TABLE `tms_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'scts-tms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-04 21:21:09
