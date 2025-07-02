import { bootstrapApplication } from '@angular/platform-browser';
// import { payementComponent } from './app/payement/payementComponent'; // <-- corriger ici
import { App } from './app/app'; // ✅ Corrigé ici
// import { importProvidersFrom } from '@angular/core';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { provideHttpClient, withFetch } from '@angular/common/http';
import { appConfig } from './app/app.config';
import { routes } from './app/app.routes';
import { provideRouter } from '@angular/router';
import { MatNativeDateModule } from '@angular/material/core';
import { importProvidersFrom } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
// import 'zone.js';

bootstrapApplication(App, {
  ...appConfig,
  providers: [provideHttpClient(),
    ...(appConfig.providers || []),
    provideRouter(routes),
    importProvidersFrom(MatNativeDateModule),
  ]
})
.catch(err => console.error(err));
