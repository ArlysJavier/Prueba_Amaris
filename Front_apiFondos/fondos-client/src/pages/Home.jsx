import { useEffect, useState } from "react";
import {
  getFondos,
  suscribirAFondo,
  cancelarFondo,
  getSaldo,
} from "../services/fondoService";
import FondoCard from "../components/FondoCard";

export default function Home() {
  const [fondos, setFondos] = useState([]);
  const [saldo, setSaldo] = useState(0);

  const cargarDatos = async () => {
    const [fRes, sRes] = await Promise.all([getFondos(), getSaldo()]);
    setFondos(fRes.data);
    setSaldo(sRes.data);
  };

  useEffect(() => {
    cargarDatos();
  }, []);

  const suscribir = async (id) => {
    const medio = window.prompt("¿Desea notificación por EMAIL o SMS?", "EMAIL");
    const res = await suscribirAFondo(id, medio);
    alert(res.data);
    cargarDatos();
  };

  const cancelar = async (id) => {
    const res = await cancelarFondo(id);
    alert(res.data);
    cargarDatos();
  };

  return (
    <div style={{ padding: 20 }}>
      <h1>Fondos disponibles</h1>
      <h3>Saldo disponible: ${saldo.toLocaleString()}</h3>
      {fondos.map((fondo) => (
        <FondoCard
          key={fondo.id}
          fondo={fondo}
          onSuscribir={suscribir}
          onCancelar={cancelar}
        />
      ))}
    </div>
  );
}
