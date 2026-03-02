import { Component, inject, signal } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ReservaService } from '../../services/reserva.service';
import { ToastComponent } from '../../shared/toast/toast.component';

/** Servicios disponibles en el sistema de reservas */
export const SERVICIOS_DISPONIBLES = [
  'Corte de pelo',
  'Corte y barba',
  'Manicura',
  'Pedicura',
  'Masaje',
  'Tinte',
  'Tratamiento facial',
  'Peinado'
] as const;

@Component({
  selector: 'app-reserva-form',
  standalone: true,
  imports: [ReactiveFormsModule, ToastComponent],
  templateUrl: './reserva-form.component.html',
  styleUrl: './reserva-form.component.css'
})
export class ReservaFormComponent {
  private readonly fb = inject(FormBuilder);
  private readonly reservaService = inject(ReservaService);
  private readonly router = inject(Router);

  servicios = SERVICIOS_DISPONIBLES;
  enviando = signal(false);
  toastVisible = signal(false);
  toastMessage = signal('');

  formulario = this.fb.group({
    nombreCliente: ['', [Validators.required, Validators.minLength(2)]],
    fecha: ['', Validators.required],
    hora: ['', Validators.required],
    servicio: ['', Validators.required]
  });

  enviar(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    this.enviando.set(true);
    this.toastVisible.set(false);

    const valor = this.formulario.getRawValue();
    const reserva = {
      customerName: valor.nombreCliente!,
      date: valor.fecha!,
      time: valor.hora!,
      service: valor.servicio!
    };

    this.reservaService.crear(reserva).subscribe({
      next: () => {
        this.enviando.set(false);
        this.router.navigate(['/reservas']);
      },
      error: (err) => {
        this.enviando.set(false);
        this.toastMessage.set(err.error?.message ?? err.message ?? 'Error al guardar la reserva');
        this.toastVisible.set(true);
      }
    });
  }

  cerrarToast(): void {
    this.toastVisible.set(false);
  }
}
