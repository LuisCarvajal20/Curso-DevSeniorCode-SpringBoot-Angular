import { Routes } from '@angular/router';
import { ReservasListComponent } from './pages/reservas-list/reservas-list.component';
import { ReservaFormComponent } from './pages/reserva-form/reserva-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'reservas', pathMatch: 'full' },
  { path: 'reservas', component: ReservasListComponent },
  { path: 'reservas/nueva', component: ReservaFormComponent }
];
