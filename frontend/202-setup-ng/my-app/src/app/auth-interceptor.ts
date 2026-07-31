import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const cloned = req.clone({
    setHeaders: {
      Authorization: 'Bearer <insert-your-token-here>', // replace with your auth token logic
    },
  });
  return next(cloned);
};
