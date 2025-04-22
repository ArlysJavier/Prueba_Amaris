# Plataforma de Fondos - Despliegue en AWS con CloudFormation

Este proyecto despliega una aplicación fullstack con:
- Backend en Java (Spring Boot) ejecutándose en EC2
- Frontend en React hospedado en un bucket S3 con hosting estático

---

## 📦 Requisitos Previos

- Cuenta de AWS activa
- CLI de AWS configurada (`aws configure`)
- Un par de llaves EC2 existente (`.pem`)
- Acceso a crear buckets S3 y stacks de CloudFormation

---

## 🚀 Despliegue del Backend (Spring Boot)

### 1. Empaquetar la app

Desde el backend Java (Spring Boot):

```bash
./mvnw clean package
```

Esto generará un `.jar` en `target/` (por defecto: `fondos-backend.jar`).

### 2. Subir el JAR a S3

```bash
aws s3 cp target/fondos-backend.jar s3://<nombre-de-tu-bucket>
```

### 3. Desplegar CloudFormation del backend

```bash
aws cloudformation deploy \
  --template-file backend-stack.yaml \
  --stack-name BackendFondos \
  --parameter-overrides \
      KeyName=tu-key-ec2 \
      JarS3Bucket=nombre-de-tu-bucket \
  --capabilities CAPABILITY_NAMED_IAM
```

### 4. Obtener IP pública de la EC2

```bash
aws cloudformation describe-stacks \
  --stack-name BackendFondos \
  --query "Stacks[0].Outputs[?OutputKey=='BackendInstancePublicIp'].OutputValue" \
  --output text
```

Accede a tu backend en:  
`http://<ip-publica>:8080/fondos`

---

## 🌐 Despliegue del Frontend (React)

### 1. Crear build

Desde la carpeta del frontend React:

```bash
npm install
npm run build
```

### 2. Desplegar CloudFormation del frontend

```bash
aws cloudformation deploy \
  --template-file frontend-stack.yaml \
  --stack-name FrontendFondos
```

### 3. Obtener URL del sitio

```bash
aws cloudformation describe-stacks \
  --stack-name FrontendFondos \
  --query "Stacks[0].Outputs[?OutputKey=='ReactAppBucketWebsiteURL'].OutputValue" \
  --output text
```

### 4. Subir el contenido del build al bucket

```bash
aws s3 sync dist/ s3://<nombre-del-bucket-creado-por-el-stack>
```

Luego accede al sitio web en la URL pública retornada.

---

## ✅ Notas adicionales

- Asegúrate de habilitar el puerto 8080 en tu grupo de seguridad EC2.
- Si usas HTTPS, puedes integrar CloudFront + ACM.
- En producción se recomienda usar Elastic Beanstalk, ECS o Fargate para backend.