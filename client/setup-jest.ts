import {setupZoneTestEnv} from 'jest-preset-angular/setup-env/zone';
import {BrowserDynamicTestingModule, platformBrowserDynamicTesting} from '@angular/platform-browser-dynamic/testing';
import 'jest-preset-angular/setup-jest';
import {TestBed} from '@angular/core/testing';

try {
  TestBed.initTestEnvironment(
    BrowserDynamicTestingModule,
    platformBrowserDynamicTesting()
  );
  setupZoneTestEnv();
} catch (error) {
  // Ignorez l'erreur si l'environnement est déjà initialisé
  console.error(error);
}

