
CREATE TABLE [Categories] (
  [CategoryID] int PRIMARY KEY IDENTITY(1, 1),
  [CategoryName] nvarchar(255),
  [Description] nvarchar(255),
  [Active] int
)
GO

CREATE TABLE [Brands] (
  [BrandID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [BrandName] nvarchar(20),
  [BrandLogo] varchar(20)
)
GO

CREATE TABLE [Users] (
  [UserID] int NOT NULL IDENTITY(1, 1),
  [Username] nvarchar(40) NOT NULL,
  [Password] nvarchar(40),
  [Role] int,
  [FullName] varchar(100) NOT NULL,
  [City] nvarchar(15),
  [Country] nvarchar(15),
  [Address] nvarchar(60),
  [Phone] nvarchar(24),
  PRIMARY KEY ([UserID])
)
GO

CREATE TABLE [Payment] (
  [PaymentID] int PRIMARY KEY IDENTITY(1, 1),
  [PaymentType] nvarchar(255),
  [Allowed] int
)
GO

CREATE TABLE [Orders] (
  [OrderID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [UserID] int,
  [TotalPrice] money NOT NULL DEFAULT (0),
  [OrderDate] datetime,
  [ShippedDate] datetime,
  [PaymentID] int
)
GO

CREATE TABLE [Products] (
  [ProductID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [ProductName] nvarchar(40) NOT NULL,
  [BrandID] int,
  [Price] money DEFAULT (0),
  [DiscountedPrice] money DEFAULT (0),
  [Status] nvarchar(255) NOT NULL CHECK ([Status] IN ('out_of_stock', 'in_stock', 'running_low'))
)
GO

CREATE TABLE [CatePro] (
  [ProductID] int NOT NULL,
  [CategoryID] int NOT NULL
)
GO

CREATE TABLE [ProductDetails] (
  [ProductID] int NOT NULL,
  [Color] nvarchar(20),
  [Quantity] nvarchar(20),
  [Image] varchar(20)
)
GO

CREATE TABLE [Items] (
  [OrderID] int NOT NULL,
  [ProductID] int NOT NULL,
  [Quantity] smallint NOT NULL DEFAULT (1)
)
GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([UserID]) REFERENCES [Users] ([UserID])
GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([PaymentID]) REFERENCES [Payment] ([PaymentID])
GO

ALTER TABLE [Products] ADD FOREIGN KEY ([BrandID]) REFERENCES [Brands] ([BrandID])
GO

ALTER TABLE [CatePro] ADD FOREIGN KEY ([ProductID]) REFERENCES [Products] ([ProductID])
GO

ALTER TABLE [CatePro] ADD FOREIGN KEY ([CategoryID]) REFERENCES [Categories] ([CategoryID])
GO

ALTER TABLE [ProductDetails] ADD FOREIGN KEY ([ProductID]) REFERENCES [Products] ([ProductID])
GO

ALTER TABLE [Items] ADD FOREIGN KEY ([OrderID]) REFERENCES [Orders] ([OrderID])
GO

ALTER TABLE [Items] ADD FOREIGN KEY ([ProductID]) REFERENCES [Products] ([ProductID])
GO

CREATE UNIQUE INDEX [Products_index_1] ON [Products] ("ProductID")
GO
