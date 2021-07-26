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
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';

import { UserlistPageComponent } from './pages/userlist-page/userlist-page.component';
import { GunlistPageComponent } from './pages/gunlist-page/gunlist-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GunlistComponent } from './components/gunlist/gunlist.component';
import { AddEditGunDialogComponent } from './components/dialogs/add-edit-gun-dialog/add-edit-gun-dialog.component';
import { AddEditModelDialogComponent } from './components/dialogs/add-edit-model-dialog/add-edit-model-dialog.component';
import { AddEditDictionaryDialogComponent } from './components/dialogs/add-edit-dictionary-dialog/add-edit-dictionary-dialog.component';
import { DeleteGenericDialogComponent } from './components/dialogs/delete-generic-dialog/delete-generic-dialog.component';
import { MaterialModule } from './modules/material/material.module';

@NgModule({
  declarations: [
    AppComponent,
    UserlistPageComponent,
    GunlistPageComponent,
    LoginPageComponent,
    NavbarComponent,
    GunlistComponent,
    AddEditGunDialogComponent,
    AddEditModelDialogComponent,
    AddEditDictionaryDialogComponent,
    DeleteGenericDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
