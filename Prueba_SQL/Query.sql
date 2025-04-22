
¿Qué hace esta consulta?
	Para cada cliente c con inscripción a un producto p, buscamos que:
	No exista una sucursal donde esté disponible ese producto (Disponibilidad)
o	Que no haya sido visitada por ese cliente (Visitan)
Es decir, todas las sucursales donde está el producto, deben haber sido visitadas por el cliente.


sql
CopiarEditar
SELECT DISTINCT c.nombre
FROM Cliente c
JOIN Inscripcion i ON c.id = i.idCliente
JOIN Producto p ON i.idProducto = p.id
WHERE NOT EXISTS (
  SELECT 1
  FROM Disponibilidad d
  WHERE d.idProducto = p.id
    AND NOT EXISTS (
      SELECT 1
      FROM Visitan v
      WHERE v.idSucursal = d.idSucursal
        AND v.idCliente = c.id
    )
);
