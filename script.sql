USE [master]
GO
/****** Object:  Database [Yellow_Moon_Shop_Management]    Script Date: 9/21/2021 1:03:09 PM ******/
CREATE DATABASE [Yellow_Moon_Shop_Management]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Yellow_Moon_Shop_Management', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Yellow_Moon_Shop_Management.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Yellow_Moon_Shop_Management_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Yellow_Moon_Shop_Management_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Yellow_Moon_Shop_Management].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET ARITHABORT OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET RECOVERY FULL 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET  MULTI_USER 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Yellow_Moon_Shop_Management', N'ON'
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET QUERY_STORE = OFF
GO
USE [Yellow_Moon_Shop_Management]
GO
/****** Object:  Table [dbo].[Cakes]    Script Date: 9/21/2021 1:03:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cakes](
	[cakeID] [varchar](10) NOT NULL,
	[cakeName] [nvarchar](30) NULL,
	[image] [varchar](max) NULL,
	[description] [nvarchar](100) NULL,
	[price] [int] NULL,
	[amount] [int] NULL,
	[createDate] [date] NULL,
	[expDate] [date] NULL,
	[categoryID] [varchar](10) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[cakeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 9/21/2021 1:03:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[categoryID] [varchar](10) NOT NULL,
	[catName] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MethodPays]    Script Date: 9/21/2021 1:03:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MethodPays](
	[methodID] [int] NOT NULL,
	[methodName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[methodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 9/21/2021 1:03:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[orderID] [int] NOT NULL,
	[cakeID] [varchar](10) NOT NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC,
	[cakeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 9/21/2021 1:03:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[orderDate] [datetime] NULL,
	[method] [int] NULL,
	[statusPayment] [bit] NULL,
	[address] [nvarchar](50) NULL,
	[totalCost] [int] NULL,
	[userID] [varchar](10) NULL,
	[phoneNumber] [varchar](20) NULL,
	[nameCustomer] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UpdateDetails]    Script Date: 9/21/2021 1:03:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UpdateDetails](
	[userID] [varchar](10) NOT NULL,
	[cakeID] [varchar](10) NOT NULL,
	[updateDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC,
	[cakeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 9/21/2021 1:03:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[userID] [varchar](10) NOT NULL,
	[password] [varchar](10) NULL,
	[fullName] [nvarchar](30) NULL,
	[role] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C01', N'Nhan thit', N'img01.jpg', N'ngon', 150, 8, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'TT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C02', N'Khong nhan', N'img02.jpg', N'ngon', 200, 2, CAST(N'2021-09-07' AS Date), CAST(N'2021-11-08' AS Date), N'XT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C03', N'Huong dau', N'img03.jpg', N'ngon', 150, 49, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'XT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C04', N'Huong cam', N'img05.jpg', N'ngot', 12, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-12-12' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C05', N'Mui buoi', N'img04.jpg', N'ngon', 120, 18, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C06', N'Nhan thit', N'img06.jpg', N'ngon', 150, 43, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C07', N'Chay', N'img07.jpg', N'ngon', 132, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C08', N'Banh ngot', N'img07.jpg', N'ngon', 130, 48, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C09', N'Banh ngot', N'img06.jpg', N'ngon', 160, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C10', N'Nhan thit', N'img05.jpg', N'ngon', 156, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C11', N'Hai san', N'img02.jpg', N'ngon', 160, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C12', N'Hai san', N'img01.jpg', N'ngon', 176, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C13', N'Thit heo', N'img02.jpg', N'ngon', 151, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'TT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C14', N'Huong cam', N'img01.jpg', N'ngon', 153, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'XT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C15', N'Nhan thit', N'img01.jpg', N'ngon', 150, 40, CAST(N'2021-10-07' AS Date), CAST(N'2021-10-14' AS Date), N'TT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C16', N'Khong nhan', N'img02.jpg', N'ngon', 200, 5, CAST(N'2021-09-07' AS Date), CAST(N'2021-11-08' AS Date), N'XT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C17', N'Huong dau', N'img03.jpg', N'ngon', 150, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'XT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C18', N'Huong buoi', N'img04.jpg', N'ngon', 12, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-11-07' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C19', N'Mui cam', N'img05.jpg', N'ngon', 120, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-11-07' AS Date), N'TT', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C20', N'Nhan thit', N'img06.jpg', N'ngon', 150, 37, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C21', N'Chay', N'img07.jpg', N'ngon', 132, 48, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C22', N'Banh ngot', N'img08.jpg', N'ngon', 130, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-12-08' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C23', N'Banh ngot', N'img02.jpg', N'ngon', 160, 40, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-09' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C24', N'Nhan thit', N'img02.jpg', N'ngon', 156, 22, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-06' AS Date), N'BC', 0)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C25', N'Hai san', N'img07.jpg', N'ngon', 160, 26, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-03' AS Date), N'BC', 0)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C26', N'Hai san', N'img07.jpg', N'ngon', 176, 50, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-09' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C27', N'Thit  bo', N'img06.jpg', N'ngon', 147, 222, CAST(N'2021-09-07' AS Date), CAST(N'2021-12-12' AS Date), N'CC', 0)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C28', N'Huong buoi', N'img05.jpg', N'ngon', 58, 46, CAST(N'2021-09-07' AS Date), CAST(N'2021-10-07' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C66', N'noname', N'img02.jpg', N'none', 30, 20, CAST(N'2015-12-12' AS Date), CAST(N'2021-12-12' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C665', N'Banh cuon', N'img02.jpg', N'ngon', 20, 20, CAST(N'2011-12-12' AS Date), CAST(N'2021-12-12' AS Date), N'CC', 0)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C669', N'noname', N'img03.jpg', N'no', 20, 20, CAST(N'2020-12-12' AS Date), CAST(N'2021-12-12' AS Date), N'CC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C98', N'DemoCake', N'img04.jpg', N'nooo', 20, 20, CAST(N'2020-12-12' AS Date), CAST(N'2021-12-12' AS Date), N'BC', 1)
INSERT [dbo].[Cakes] ([cakeID], [cakeName], [image], [description], [price], [amount], [createDate], [expDate], [categoryID], [status]) VALUES (N'C99', N'345344', N'img01.jpg', N'34535', 3634, 34636, CAST(N'2010-12-12' AS Date), CAST(N'2022-12-12' AS Date), N'BC', 1)
GO
INSERT [dbo].[Categories] ([categoryID], [catName]) VALUES (N'BC', N'Banh cuon')
INSERT [dbo].[Categories] ([categoryID], [catName]) VALUES (N'CC', N'Banh chuoi chien')
INSERT [dbo].[Categories] ([categoryID], [catName]) VALUES (N'TT', N'Banh trung thu')
INSERT [dbo].[Categories] ([categoryID], [catName]) VALUES (N'XT', N'Banh xeo chao')
GO
INSERT [dbo].[MethodPays] ([methodID], [methodName]) VALUES (1, N'Payment on delivery')
INSERT [dbo].[MethodPays] ([methodID], [methodName]) VALUES (2, N'Wallet electronic')
GO
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (26, N'C05', 5)
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (26, N'C06', 1)
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (27, N'C05', 6)
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (27, N'C08', 1)
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (28, N'C05', 5)
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (28, N'C06', 1)
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (29, N'C05', 1)
INSERT [dbo].[OrderDetails] ([orderID], [cakeID], [quantity]) VALUES (29, N'C28', 4)
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([orderID], [orderDate], [method], [statusPayment], [address], [totalCost], [userID], [phoneNumber], [nameCustomer]) VALUES (26, CAST(N'2021-09-20T20:08:04.377' AS DateTime), 1, 1, N'123', 750, NULL, N'1233123123', N'tann')
INSERT [dbo].[Orders] ([orderID], [orderDate], [method], [statusPayment], [address], [totalCost], [userID], [phoneNumber], [nameCustomer]) VALUES (27, CAST(N'2021-09-20T20:10:48.400' AS DateTime), 1, 1, N'phu yen', 850, N'U01', N'1345698799', N'nguyen')
INSERT [dbo].[Orders] ([orderID], [orderDate], [method], [statusPayment], [address], [totalCost], [userID], [phoneNumber], [nameCustomer]) VALUES (28, CAST(N'2021-09-20T22:20:05.183' AS DateTime), 1, 1, N'phu yen', 750, NULL, N'123456789', N'tannnn')
INSERT [dbo].[Orders] ([orderID], [orderDate], [method], [statusPayment], [address], [totalCost], [userID], [phoneNumber], [nameCustomer]) VALUES (29, CAST(N'2021-09-20T22:22:23.957' AS DateTime), 1, 1, N'song cau', 352, N'U01', N'13246578954', N'thang')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
INSERT [dbo].[UpdateDetails] ([userID], [cakeID], [updateDate]) VALUES (N'A01', N'C05', CAST(N'2021-09-17' AS Date))
INSERT [dbo].[UpdateDetails] ([userID], [cakeID], [updateDate]) VALUES (N'A01', N'C24', CAST(N'2021-09-14' AS Date))
INSERT [dbo].[UpdateDetails] ([userID], [cakeID], [updateDate]) VALUES (N'A01', N'C25', CAST(N'2021-09-20' AS Date))
INSERT [dbo].[UpdateDetails] ([userID], [cakeID], [updateDate]) VALUES (N'A01', N'C28', CAST(N'2021-09-20' AS Date))
INSERT [dbo].[UpdateDetails] ([userID], [cakeID], [updateDate]) VALUES (N'A01', N'C66', CAST(N'2021-09-20' AS Date))
GO
INSERT [dbo].[Users] ([userID], [password], [fullName], [role]) VALUES (N'A01', N'123', N'Thang Nguyen', 2)
INSERT [dbo].[Users] ([userID], [password], [fullName], [role]) VALUES (N'A02', N'123', N'Admin', 2)
INSERT [dbo].[Users] ([userID], [password], [fullName], [role]) VALUES (N'U01', N'123', N'Tan Nguyen', 1)
INSERT [dbo].[Users] ([userID], [password], [fullName], [role]) VALUES (N'U02', N'123', N'Loi Nguyen', 1)
GO
ALTER TABLE [dbo].[Cakes]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[Categories] ([categoryID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD FOREIGN KEY([cakeID])
REFERENCES [dbo].[Cakes] ([cakeID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[Orders] ([orderID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([method])
REFERENCES [dbo].[MethodPays] ([methodID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[UpdateDetails]  WITH CHECK ADD FOREIGN KEY([cakeID])
REFERENCES [dbo].[Cakes] ([cakeID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[UpdateDetails]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
USE [master]
GO
ALTER DATABASE [Yellow_Moon_Shop_Management] SET  READ_WRITE 
GO
