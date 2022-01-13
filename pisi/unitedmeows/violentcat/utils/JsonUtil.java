package pisi.unitedmeows.violentcat.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

public class JsonUtil {

	public static String getString(JsonElement jsonElement) {
		return jsonElement == JsonNull.INSTANCE ? null : jsonElement.getAsString();
	}

	public static int getInt(JsonElement jsonElement) {
		return jsonElement == JsonNull.INSTANCE ? -1 : jsonElement.getAsInt();
	}
}
