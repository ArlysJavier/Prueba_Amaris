export default function FondoCard({ fondo, onSuscribir, onCancelar }) {
    return (
      <div style={{ border: "1px solid #ccc", padding: 10, marginBottom: 10 }}>
        <h3>{fondo.nombre}</h3>
        <p>Monto mínimo: ${fondo.montoMinimo.toLocaleString()}</p>
        <p>Categoría: {fondo.categoria}</p>
        <button onClick={() => onSuscribir(fondo.id)}>Suscribirse</button>
        <button onClick={() => onCancelar(fondo.id)} style={{ marginLeft: 10 }}>
          Cancelar
        </button>
      </div>
    );
  }
  