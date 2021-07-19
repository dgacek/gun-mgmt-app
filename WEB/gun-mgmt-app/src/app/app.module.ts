import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { UserlistPageComponent } from './pages/userlist-page/userlist-page.component';
import { GunlistPageComponent } from './pages/gunlist-page/gunlist-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GunlistComponent } from './components/gunlist/gunlist.component';
import { AddGunDialogComponent } from './components/dialogs/add-gun-dialog/add-gun-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    UserlistPageComponent,
    GunlistPageComponent,
    LoginPageComponent,
    NavbarComponent,
    GunlistComponent,
    AddGunDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    MatTableModule,
    MatSortModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
