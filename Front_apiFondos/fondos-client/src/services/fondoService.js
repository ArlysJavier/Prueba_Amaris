import axios from "axios";

const API = "http://localhost:8080/fondos";

export const getFondos = () => axios.get(API);
export const suscribirAFondo = (idFondo, medioNotificacion) =>
  axios.post(`${API}/suscribir`, { idFondo, medioNotificacion });
export const cancelarFondo = (idFondo) =>
  axios.post(`${API}/cancelar/${idFondo}`);
export const getHistorial = () => axios.get(`${API}/historial`);
export const getSaldo = () => axios.get(`${API}/saldo`);
