import { Component, input, output } from '@angular/core';

/**
 * Componente toast para mostrar mensajes de notificación o error.
 */
@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.css'
})
export class ToastComponent {
  message = input.required<string>();
  visible = input<boolean>(false);
  type = input<'error' | 'success' | 'info'>('error');

  closed = output<void>();

  cerrar(): void {
    this.closed.emit();
  }
}
