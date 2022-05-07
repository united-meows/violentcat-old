package pisi.unitedmeows.violentcat.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pisi.unitedmeows.eventapi.event.utils.TypeResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class GsonWrap<Result> {

	private JsonObject jsonObject;

	private static GsonBuilder builder = new GsonBuilder();

	public GsonWrap(JsonObject _jsonObject) {
		jsonObject = _jsonObject;
	}

	public GsonWrap(String src) {
		this(new JsonParser().parse(src).getAsJsonObject());
	}

	@SuppressWarnings("unchecked")
	public Result build() {
		Class<?> type = (Class<?>) TypeResolver.resolveRawArgument(GsonWrap.class, this.getClass());

		Result result;
		try {
			result = (Result) type.newInstance();
			result = (Result) builder.create().fromJson(jsonObject, result.getClass());
		} catch (Exception ex) {
			return null;
		}


		return result;
	}
}
