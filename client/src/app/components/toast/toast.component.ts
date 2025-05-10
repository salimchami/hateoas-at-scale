import {Component, OnDestroy, OnInit} from "@angular/core";
import {ToastMessage, ToastService} from "./toast.service";
import {Subscription} from "rxjs";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  standalone: true,
  imports: [
    CommonModule
  ]
})
export class ToastComponent implements OnInit, OnDestroy {
  toast: ToastMessage | null = null;
  private subscription!: Subscription;

  constructor(private readonly toastService: ToastService) {
  }

  ngOnInit(): void {
    this.subscription = this.toastService.toastState$.subscribe((toast) => {
      this.toast = toast;
    });
  }


  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  closeToast() {
    this.toast = null;
  }

  clearToastTimeout() {
    this.toastService.clearToastTimeout();
  }
}
