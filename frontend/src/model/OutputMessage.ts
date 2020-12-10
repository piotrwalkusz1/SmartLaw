import { decodeEnum, decodeString } from "../utils/Decoders";

export enum OutputMessageType {
  ERROR = "ERROR",
  WARNING = "WARNING",
}

export const decodeOutputMessage = (json: any): OutputMessage => {
  return new OutputMessage(decodeEnum(json.type, OutputMessageType), decodeString(json.message));
};

export class OutputMessage {
  type: OutputMessageType;
  message: String;

  constructor(type: OutputMessageType, message: String) {
    this.type = type;
    this.message = message;
  }
}
