import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'channel'
})
export class ChannelPipe implements PipeTransform {

  transform(selectedChannelId: number): number {
    return selectedChannelId;
  }

}
