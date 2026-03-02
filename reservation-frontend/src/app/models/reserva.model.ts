/**
 * Modelo que representa una reserva en el sistema.
 */
export interface Reserva {
  id: number;
  customerName: string;
  date: string;
  time: string;
  service: string;
  status: 'ACTIVA' | 'CANCELLED';
}

/**
 * DTO para crear una nueva reserva.
 */
export interface CrearReservaDto {
  customerName: string;
  date: string;
  time: string;
  service: string;
}
