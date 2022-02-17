CREATE DATABASE Demo5

CREATE TABLE [Employees] (
  [EmployeeID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [LastName] nvarchar(20) NOT NULL,
  [FirstName] nvarchar(10) NOT NULL,
  [Photo] image,
  [Title] nvarchar(30),
  [TitleOfCourtesy] nvarchar(25),
  [BirthDate] datetime,
  [HireDate] datetime,
  [Address] nvarchar(60),
  [City] nvarchar(15),
  [Country] nvarchar(15),
  [Phone] nvarchar(24),
  [Notes] ntext
)
GO

CREATE TABLE [CategoriesDetails] (
  [CategoryID] int PRIMARY KEY IDENTITY(1, 1),
  [CategoryName] nvarchar(255),
  [Description] nvarchar(255),
  [Active] int
)
GO

CREATE TABLE [Brands] (
  [BrandID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [BrandName] nvarchar(20)
)
GO

CREATE TABLE [Customers] (
  [CustomerID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [FullName] varchar(100) NOT NULL,
  [CompanyName] nvarchar(40),
  [Address] nvarchar(60),
  [City] nvarchar(15),
  [Country] nvarchar(15),
  [Phone] nvarchar(24),
  [Fax] nvarchar(24)
)
GO

CREATE TABLE [Shippers] (
  [ShipperID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [CompanyName] nvarchar(40) NOT NULL,
  [Phone] nvarchar(24)
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
  [CustomerID] int,
  [EmployeeID] int,
  [OrderDate] datetime,
  [RequiredDate] datetime,
  [ShippedDate] datetime,
  [ShipVia] int,
  [ShipName] nvarchar(40),
  [ShipAddress] nvarchar(60),
  [ShipCity] nvarchar(15),
  [ShipCountry] nvarchar(15),
  [PaymentID] int,
  [Status] nvarchar(255)
)
GO

CREATE TABLE [Products] (
  [ProductID] int PRIMARY KEY NOT NULL IDENTITY(1, 1),
  [ProductName] nvarchar(40) NOT NULL,
  [BrandID] int,
  [QuantityPerUnit] nvarchar(20),
  [UnitPrice] money DEFAULT (0),
  [UnitsInStock] smallint DEFAULT (0),
  [UnitsOnOrder] smallint DEFAULT (0),
  [Status] nvarchar(255) NOT NULL CHECK ([Status] IN ('out_of_stock', 'in_stock', 'running_low'))
)
GO

CREATE TABLE [Categories] (
  [ProductID] int NOT NULL,
  [CategoryID] int NOT NULL
)
GO

CREATE TABLE [Pictures] (
  [ID] int,
  [Image] image
)
GO

CREATE TABLE [Colors] (
  [ProductID] int NOT NULL,
  [ColorName] nvarchar(20)
)
GO

CREATE TABLE [OrderDetails] (
  [OrderID] int NOT NULL,
  [ProductID] int NOT NULL,
  [UnitPrice] money NOT NULL DEFAULT (0),
  [Quantity] smallint NOT NULL DEFAULT (1),
  [Discount] real NOT NULL DEFAULT (0)
)
GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([CustomerID]) REFERENCES [Customers] ([CustomerID])
GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([EmployeeID]) REFERENCES [Employees] ([EmployeeID])
GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([ShipVia]) REFERENCES [Shippers] ([ShipperID])
GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([PaymentID]) REFERENCES [Payment] ([PaymentID])
GO

ALTER TABLE [Products] ADD FOREIGN KEY ([BrandID]) REFERENCES [Brands] ([BrandID])
GO

ALTER TABLE [Categories] ADD FOREIGN KEY ([ProductID]) REFERENCES [Products] ([ProductID])
GO

ALTER TABLE [Categories] ADD FOREIGN KEY ([CategoryID]) REFERENCES [CategoriesDetails] ([CategoryID])
GO

ALTER TABLE [Pictures] ADD FOREIGN KEY ([ID]) REFERENCES [Products] ([ProductID])
GO

ALTER TABLE [Colors] ADD FOREIGN KEY ([ProductID]) REFERENCES [Products] ([ProductID])
GO

ALTER TABLE [OrderDetails] ADD FOREIGN KEY ([OrderID]) REFERENCES [Orders] ([OrderID])
GO

ALTER TABLE [OrderDetails] ADD FOREIGN KEY ([ProductID]) REFERENCES [Products] ([ProductID])
GO

CREATE INDEX [product_status] ON [Products] ("Status")
GO

CREATE UNIQUE INDEX [Products_index_1] ON [Products] ("ProductID")
GO
