import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GunlistPageComponent } from './pages/gunlist-page/gunlist-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { UserlistPageComponent } from './pages/userlist-page/userlist-page.component';

const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: 'guns', component: GunlistPageComponent },
  { path: 'users', component: UserlistPageComponent },
  { path: '', redirectTo: '/guns', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
