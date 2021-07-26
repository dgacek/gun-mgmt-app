import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { UserlistPageComponent } from './views/pages/userlist-page/userlist-page.component';
import { GunlistPageComponent } from './views/pages/gunlist-page/gunlist-page.component';
import { LoginPageComponent } from './views/pages/login-page/login-page.component';
import { NavbarComponent } from './views/components/navbar/navbar.component';
import { GunlistComponent } from './views/components/gunlist/gunlist.component';
import { GunFormDialogComponent } from './views/components/dialogs/gun-form-dialog/gun-form-dialog.component';
import { ModelFormDialogComponent } from './views/components/dialogs/model-form-dialog/model-form-dialog.component';
import { DictionaryFormDialogComponent } from './views/components/dialogs/dictionary-form-dialog/dictionary-form-dialog.component';
import { DeleteGenericDialogComponent } from './views/components/dialogs/delete-generic-dialog/delete-generic-dialog.component';
import { MaterialModule } from './shared/modules/material/material.module';

@NgModule({
  declarations: [
    AppComponent,
    UserlistPageComponent,
    GunlistPageComponent,
    LoginPageComponent,
    NavbarComponent,
    GunlistComponent,
    GunFormDialogComponent,
    ModelFormDialogComponent,
    DictionaryFormDialogComponent,
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
