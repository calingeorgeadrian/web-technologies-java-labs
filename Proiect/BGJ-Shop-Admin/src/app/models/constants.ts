import { Injectable } from "@angular/core";

@Injectable()
export class Constants {
  public productTypes: ProductType[] = [
    { text: 'Game', value: 'boardgame' },
    { text: 'Expansion', value: 'boardgameexpansion' },
  ];

  public codeTypes: CodeType[] = [
    { text: 'Percent', value: 0 },
    { text: 'Fixed Value', value: 1 },
  ];

  constructor() {

  }
}

export interface ProductType {
  text: string;
  value: string;
}

export interface CodeType {
  text: string;
  value: number;
}
