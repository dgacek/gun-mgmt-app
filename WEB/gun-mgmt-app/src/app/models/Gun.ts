import { DictionaryData } from "./DictionaryData";
import { Model } from "./Model";

export interface Gun {
  id: number;
  productionYear: number;
  model: Model;
  caliber: DictionaryData;
  type: DictionaryData;
}