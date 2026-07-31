import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'cute' })
export class CutePipe implements PipeTransform {
  transform(value: string, times: number): string {
    const cutes = '(｡♥‿♥｡) '.repeat(times);

    return cutes + value + cutes;
  }
}
