import { useEffect, useState } from "react";
import { getHistorial } from "../services/fondoService";

export default function Historial() {
  const [transacciones, setTransacciones] = useState([]);

  useEffect(() => {
    getHistorial().then((res) => setTransacciones(res.data));
  }, []);

  return (
    <div style={{ padding: 20 }}>
      <h1>Historial de transacciones</h1>
      {transacciones.map((t) => (
        <div key={t.id}>
          <p>
            [{t.tipo}] {t.nombreFondo} - ${t.monto.toLocaleString()} -{" "}
            {new Date(t.fecha).toLocaleString()}
          </p>
        </div>
      ))}
    </div>
  );
}
