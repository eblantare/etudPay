import { TestBed } from '@angular/core/testing';
import { importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

// import { CanActivateFn } from '@angular/router';

import { AuthGuard } from './auth-guard';

describe('AuthGuard', () => {
  // const executeGuard: CanActivateFn = (...guardParameters) => 
      // TestBed.runInInjectionContext(() => authGuard(...guardParameters));
  let guard: AuthGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
    providers:[provideRouter([]),AuthGuard]});
  });
    guard=TestBed.inject(AuthGuard);
  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
