import { init } from "./appFetch";
import * as administratorService from "./administratorService";
import * as smashCharacterService from "./smashCharacterService";
import * as comboService from "./comboService";

export { default as NetworkError } from "./NetworkError";

export default { init, administratorService, smashCharacterService, comboService};