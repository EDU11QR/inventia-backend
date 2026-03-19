# 🚀 INVENTIA - Sistema de Inventario Inteligente

INVENTIA es una aplicación web diseñada para pequeñas y medianas empresas (bodegas, ferreterías, tiendas de ropa, minimarkets) que permite gestionar inventario, registrar ventas y obtener métricas del negocio en tiempo real.

---

## 🎯 Objetivo

Optimizar la gestión de inventario y ventas mediante un sistema inteligente que automatiza procesos y proporciona información clave para la toma de decisiones.

---

## 🧠 Funcionalidades principales

### 📦 Gestión de productos
- Crear productos
- Editar productos
- Eliminar productos
- Listar productos

### 🛒 Registro de ventas
- Registro de ventas con múltiples productos
- Cálculo automático del total
- Descuento automático de stock

### ⚠️ Alertas inteligentes
- Detección automática de productos con stock bajo
- Regla: `stock <= stockMinimum`

### 📊 Dashboard
- Ingresos totales
- Número de ventas
- Ventas del día
- Productos con bajo stock

---

## 🧱 Arquitectura

Arquitectura en capas:


---

## ⚙️ Tecnologías utilizadas

### Backend
- Java 23
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL

### Frontend (en desarrollo)
- React + Vite
- Axios

---

## 🗄️ Modelo de datos

### products
- id
- name
- description
- price
- stock
- stockMinimum
- category
- createdAt

### sales
- id
- total
- date

### sale_details
- id
- product_id
- quantity
- price
- subtotal

---

## 🔗 Endpoints principales

### Productos
- GET /products
- POST /products
- PUT /products/{id}
- DELETE /products/{id}

### Ventas
- POST /sales

### Alertas
- GET /alerts/low-stock

### Dashboard
- GET /dashboard

---

## 🧪 Ejemplo de venta

```json
{
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}

## 📌 Autor

EduDev