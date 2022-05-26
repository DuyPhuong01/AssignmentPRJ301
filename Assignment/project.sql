USE [master]
GO
/****** Object:  Database [Final]    Script Date: Thu 03 24 22 9:54:39 AM ******/
CREATE DATABASE [Final]
GO
ALTER DATABASE [Final] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Final].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Final] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Final] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Final] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Final] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Final] SET ARITHABORT OFF 
GO
ALTER DATABASE [Final] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Final] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Final] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Final] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Final] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Final] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Final] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Final] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Final] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Final] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Final] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Final] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Final] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Final] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Final] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Final] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Final] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Final] SET RECOVERY FULL 
GO
ALTER DATABASE [Final] SET  MULTI_USER 
GO
ALTER DATABASE [Final] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Final] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Final] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Final] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Final] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Final] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Final', N'ON'
GO
ALTER DATABASE [Final] SET QUERY_STORE = OFF
GO
USE [Final]
GO
/****** Object:  Table [dbo].[Brands]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brands](
	[BrandID] [int] NOT NULL,
	[BrandName] [nvarchar](20) NULL,
	[BrandLogo] [varchar](200) NULL,
 CONSTRAINT [PK__Brands__DAD4F3BE0D977E03] PRIMARY KEY CLUSTERED 
(
	[BrandID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CategoryID] [int] NOT NULL,
	[CategoryName] [nvarchar](255) NULL,
	[Description] [nvarchar](255) NULL,
	[Status] [int] NULL,
 CONSTRAINT [PK__Categori__19093A2BFAF1E22E] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CatePro]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CatePro](
	[ProductID] [int] NOT NULL,
	[CategoryID] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Colors]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Colors](
	[ColorID] [varchar](6) NULL,
	[ColorName] [nvarchar](15) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Items]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Items](
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [smallint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[TotalPrice] [money] NOT NULL,
	[OrderDate] [datetime] NULL,
	[ShippedDate] [datetime] NULL,
	[PaymentID] [int] NULL,
	[Status] [int] NULL,
	[City] [nvarchar](50) NULL,
	[Country] [nvarchar](50) NULL,
	[Address] [nvarchar](60) NULL,
	[Phone] [nvarchar](24) NULL,
 CONSTRAINT [PK__Orders__C3905BAF2C7F3812] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[PaymentID] [int] IDENTITY(1,1) NOT NULL,
	[PaymentType] [nvarchar](255) NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[PaymentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductID] [int] NOT NULL,
	[ProductName] [nvarchar](80) NOT NULL,
	[BrandID] [int] NULL,
	[Price] [money] NULL,
	[DiscountedPrice] [money] NULL,
	[ColorID] [varchar](100) NULL,
	[Quantity] [nvarchar](20) NULL,
	[ProductImage] [varchar](200) NULL,
	[Status] [int] NULL,
 CONSTRAINT [PK__Products__B40CC6EDA6883074] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: Thu 03 24 22 9:54:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] NOT NULL,
	[Username] [varchar](40) NOT NULL,
	[Password] [varchar](40) NULL,
	[Role] [int] NULL,
	[FullName] [varchar](100) NOT NULL,
	[City] [nvarchar](50) NULL,
	[Country] [nvarchar](50) NULL,
	[Address] [nvarchar](60) NULL,
	[Phone] [nvarchar](24) NULL,
 CONSTRAINT [PK__Users__536C85E5D7E84CBB] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Brands] ([BrandID], [BrandName], [BrandLogo]) VALUES (1, N'Nike', N'nike-logo.png')
INSERT [dbo].[Brands] ([BrandID], [BrandName], [BrandLogo]) VALUES (2, N'Jordan', N'jordan-logo.png')
INSERT [dbo].[Brands] ([BrandID], [BrandName], [BrandLogo]) VALUES (3, N'Converse', N'converse-logo.png')
GO
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Status]) VALUES (1, N'Men', NULL, 1)
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Status]) VALUES (2, N'Women', N'', 1)
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Status]) VALUES (3, N'Kid', NULL, 1)
GO
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (12, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (13, 2)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (14, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (15, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (18, 2)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (20, 3)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (21, 2)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (16, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (1, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (2, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (3, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (4, 2)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (5, 2)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (6, 3)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (7, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (8, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (9, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (10, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (11, 1)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (19, 2)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (17, 2)
INSERT [dbo].[CatePro] ([ProductID], [CategoryID]) VALUES (22, 1)
GO
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (3, 8, 4)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (7, 3, 2)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (9, 12, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (7, 2, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (5, 5, 5)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (16, 2, 11)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (13, 19, 2)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (4, 1, 4)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (2, 2, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (12, 1, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (2, 20, 5)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (10, 6, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (14, 20, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (15, 16, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (6, 2, 2)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (8, 3, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (2, 14, 1)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (3, 5, 7)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (11, 8, 5)
INSERT [dbo].[Items] ([OrderID], [ProductID], [Quantity]) VALUES (2, 8, 4)
GO
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (1, 1, 0.0000, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (2, 2, 700.0000, CAST(N'2022-03-09T18:50:07.180' AS DateTime), NULL, NULL, 1, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (3, 1, 1500.0000, CAST(N'2022-03-09T19:46:47.267' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (4, 1, 480.0000, CAST(N'2022-03-10T16:50:31.703' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (5, 1, 700.0000, CAST(N'2022-03-12T16:09:31.343' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (6, 1, 320.0000, CAST(N'2022-03-14T02:49:29.563' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (7, 2, 580.0000, CAST(N'2022-03-14T16:21:35.300' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (8, 1, 210.0000, CAST(N'2022-03-14T16:47:06.840' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (9, 1, 200.0000, CAST(N'2022-03-14T16:47:20.823' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (10, 1, 110.0000, CAST(N'2022-03-14T16:50:55.303' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (11, 1, 650.0000, CAST(N'2022-03-14T16:52:46.083' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (12, 2, 120.0000, CAST(N'2022-03-15T04:37:50.827' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (13, 2, 120.0000, CAST(N'2022-03-15T04:40:45.770' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (14, 2, 35.0000, CAST(N'2022-03-15T04:41:40.133' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (15, 2, 75.0000, CAST(N'2022-03-15T04:43:17.570' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (16, 1, 1760.0000, CAST(N'2022-03-15T11:13:52.100' AS DateTime), NULL, NULL, 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [UserID], [TotalPrice], [OrderDate], [ShippedDate], [PaymentID], [Status], [City], [Country], [Address], [Phone]) VALUES (17, 3, 0.0000, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (1, N'Nike Air Zoom Pegasus 38', 1, 120.0000, 0.0000, NULL, N'98', N'air-zoom-pegasus-38-mens-road-running-shoes-lq7PZZ.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (2, N'Nike Pegasus Trail 3 GORE-TEX', 1, 160.0000, 0.0000, NULL, N'89', N'pegasus-trail-3-gore-tex-mens-running-shoes-HG005k.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (3, N'Nike Air VaporMax 2021 FK', 1, 210.0000, 0.0000, NULL, N'98', N'air-vapormax-2021-fk-mens-shoes-NpTfFz.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (4, N'Nike Air Force 1 ''07', 1, 100.0000, 0.0000, NULL, N'100', N'air-force-1-07-womens-shoes-GCkPzr.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (5, N'Air Jordan 1 Elevate Low SE', 1, 140.0000, 0.0000, NULL, N'100', N'air-jordan-1-elevate-low-se-womens-shoe-cHVw3J.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (6, N'Kyrie Infinity SE', 1, 110.0000, 0.0000, NULL, N'98', N'kyrie-infinity-se-big-kids-basketball-shoes-hX4VKQ.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (7, N'Nike Blazer Mid ''77 Vintage', 1, 100.0000, 0.0000, NULL, N'100', N'blazer-mid-77-vintage-mens-shoes-nw30B2.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (8, N'Nike Air Huarache', 1, 130.0000, 0.0000, NULL, N'95', N'air-huarache-mens-shoes-JppwBb.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (9, N'Jordan .5 ''Why Not?''', 2, 130.0000, 0.0000, NULL, N'100', N'jordan-5-why-not-basketball-shoes-6V8Hzg.jpeg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (10, N'Air Jordan 1 Mid Paris', 2, 130.0000, 0.0000, NULL, N'100', N'air-jordan-1-mid-paris-mens-shoes-n65720.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (11, N'Jordan Point Lane', 2, 132.0000, 0.0000, NULL, N'100', N'jordan-point-lane-mens-shoes-PPMHdC.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (12, N'Air Jordan 6 Retro', 1, 200.0000, 0.0000, NULL, N'98', N'air-jordan-6-retro-womens-shoe-42wg9K.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (13, N'Jordan Series', 1, 80.0000, 0.0000, NULL, N'100', N'jordan-series-womens-shoes-9LvBqb.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (14, N'Chuck Taylor All Star CX Stretch Canvas', 3, 70.0000, 0.0000, NULL, N'100', N'172809C_A_107X1.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (15, N'Chuck 70 Tearaway', 3, 105.0000, 0.0000, NULL, N'100', N'172936C_A_107X1.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (16, N'Chuck Taylor All Star Pro Suede', 3, 75.0000, 0.0000, NULL, N'99', N'172631C_A_107X1.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (17, N'It''s Okay To Wander Chuck Taylor All Star', 3, 70.0000, 0.0000, NULL, N'100', N'571079C_A_107X1.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (18, N'Summer Spirit Chuck Taylor All Star', 3, 70.0000, 0.0000, NULL, N'100', N'571925C_A_107X1.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (19, N'Chuck Taylor All Star', 3, 60.0000, 0.0000, NULL, N'98', N'M7650_A_107X1.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (20, N'Chuck Taylor All Star Easy-On Rainbow Castles', 3, 35.0000, 0.0000, NULL, N'99', N'772945C_A_107X1.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (21, N'Nike Dunk High Up', 1, 120.0000, 0.0000, NULL, N'100', N'af8d5d18-6c96-4f65-b26d-679c6548768a.jpg', 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [BrandID], [Price], [DiscountedPrice], [ColorID], [Quantity], [ProductImage], [Status]) VALUES (22, N'Nike Blazer Low Platform', 1, 105.0000, 0.0000, NULL, N'100', N'7d246e2a-1c3c-41ef-a20a-2d01e09a9dce.jpg', 1)
GO
INSERT [dbo].[Users] ([UserID], [Username], [Password], [Role], [FullName], [City], [Country], [Address], [Phone]) VALUES (1, N'phuong', N'15102001', 1, N'Nguyen Ly Duy Phuong', N'', N'', N'', N'')
INSERT [dbo].[Users] ([UserID], [Username], [Password], [Role], [FullName], [City], [Country], [Address], [Phone]) VALUES (2, N'duy phuong', N'1510', 1, N'phuong duy', N'Thị xã Sơn Tây', N'Việt Nam', N'N/A', N'+84989576428')
INSERT [dbo].[Users] ([UserID], [Username], [Password], [Role], [FullName], [City], [Country], [Address], [Phone]) VALUES (3, N'rei', N'123', 0, N'rei rei', N'', N'', N'', N'')
GO
ALTER TABLE [dbo].[Items] ADD  DEFAULT ((1)) FOR [Quantity]
GO
ALTER TABLE [dbo].[Orders] ADD  CONSTRAINT [DF__Orders__TotalPri__2D27B809]  DEFAULT ((0)) FOR [TotalPrice]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF__Products__Price__300424B4]  DEFAULT ((0)) FOR [Price]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF__Products__Discou__30F848ED]  DEFAULT ((0)) FOR [DiscountedPrice]
GO
ALTER TABLE [dbo].[CatePro]  WITH CHECK ADD  CONSTRAINT [FK__CatePro__Categor__38996AB5] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Categories] ([CategoryID])
GO
ALTER TABLE [dbo].[CatePro] CHECK CONSTRAINT [FK__CatePro__Categor__38996AB5]
GO
ALTER TABLE [dbo].[CatePro]  WITH CHECK ADD  CONSTRAINT [FK__CatePro__Product__37A5467C] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[CatePro] CHECK CONSTRAINT [FK__CatePro__Product__37A5467C]
GO
ALTER TABLE [dbo].[Items]  WITH CHECK ADD  CONSTRAINT [FK__Items__OrderID__398D8EEE] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[Items] CHECK CONSTRAINT [FK__Items__OrderID__398D8EEE]
GO
ALTER TABLE [dbo].[Items]  WITH CHECK ADD  CONSTRAINT [FK__Items__ProductID__3A81B327] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[Items] CHECK CONSTRAINT [FK__Items__ProductID__3A81B327]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__PaymentI__35BCFE0A] FOREIGN KEY([PaymentID])
REFERENCES [dbo].[Payment] ([PaymentID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__PaymentI__35BCFE0A]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Users] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Users]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK__Products__BrandI__36B12243] FOREIGN KEY([BrandID])
REFERENCES [dbo].[Brands] ([BrandID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK__Products__BrandI__36B12243]
GO
USE [master]
GO
ALTER DATABASE [Final] SET  READ_WRITE 
GO
