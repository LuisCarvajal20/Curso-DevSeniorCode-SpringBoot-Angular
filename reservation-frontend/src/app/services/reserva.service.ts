import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import type { Reserva, CrearReservaDto } from '../models/reserva.model';

/**
 * Servicio para gestionar las operaciones CRUD de reservas.
 * Utiliza HttpClient para comunicarse con el backend.
 */
@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/reservas`;

  /**
   * Obtiene todas las reservas del sistema.
   * @returns Observable con el array de reservas
   */
  obtenerTodas(): Observable<Reserva[]> {
    return this.http.get<Reserva[]>(this.apiUrl);
  }

  /**
   * Crea una nueva reserva.
   * @param reserva - Datos de la reserva a crear
   * @returns Observable con la reserva creada
   */
  crear(reserva: CrearReservaDto): Observable<Reserva> {
    return this.http.post<Reserva>(this.apiUrl, reserva);
  }

  /**
   * Cancela una reserva existente.
   * @param id - Identificador de la reserva a cancelar
   * @returns Observable que se completa al cancelar
   */
  cancelar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
