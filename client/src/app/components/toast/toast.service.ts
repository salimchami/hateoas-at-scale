import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {ToastType} from "./toast.type";

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  private readonly toastSubject = new Subject<ToastMessage | null>();
  toastState$ = this.toastSubject.asObservable();
  private timeoutId!: ReturnType<typeof setTimeout>;

  showToast(type: ToastType, title: string, message: string, duration: number = 10000) {
    clearTimeout(this.timeoutId);
    this.toastSubject.next({type, title, message, duration});
    this.timeoutId = this.clearToast(duration);
  }

  clearToastTimeout() {
    clearTimeout(this.timeoutId);
  }

  clearToast(duration: number): ReturnType<typeof setTimeout> {
    return setTimeout(() => {
      this.toastSubject.next(null);
    }, duration);
  }
}

export interface ToastMessage {
  type: ToastType;
  title: string;
  message: string;
  duration: number;
}
