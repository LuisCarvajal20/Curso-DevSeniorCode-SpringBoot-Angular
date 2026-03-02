import { Component, inject, signal } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterLink } from '@angular/router';
import { ReservaService } from '../../services/reserva.service';
import type { Reserva } from '../../models/reserva.model';

@Component({
  selector: 'app-reservas-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './reservas-list.component.html',
  styleUrl: './reservas-list.component.css'
})
export class ReservasListComponent {
  private readonly reservaService = inject(ReservaService);

  reservas = signal<Reserva[]>([]);
  loading = signal(false);
  error = signal<string | null>(null);

  constructor() {
    this.cargarReservas();
  }

  cargarReservas(): void {
    this.loading.set(true);
    this.error.set(null);

    this.reservaService.obtenerTodas().subscribe({
      next: (data) => {
        this.reservas.set(data);
        this.loading.set(false);
      },
      error: (err: HttpErrorResponse) => {
        this.error.set(this.obtenerMensajeError(err, 'Error al cargar las reservas'));
        this.loading.set(false);
      }
    });
  }

  cancelarReserva(id: number): void {
    this.reservaService.cancelar(id).subscribe({
      next: () => this.cargarReservas(),
      error: (err: HttpErrorResponse) =>
        this.error.set(this.obtenerMensajeError(err, 'Error al cancelar la reserva'))
    });
  }

  private obtenerMensajeError(err: HttpErrorResponse, mensajePorDefecto: string): string {
    if (err.status === 0) {
      return 'No se pudo conectar con el servidor. ¿Está el backend en ejecución en el puerto configurado?';
    }
    if (err.error instanceof ProgressEvent) {
      return 'Error de red o CORS. Verifica que el backend esté en ejecución.';
    }
    const msg = err.error?.message ?? err.error?.error ?? err.message;
    return msg ?? mensajePorDefecto;
  }
}
