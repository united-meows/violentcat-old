package pisi.unitedmeows.violentcat.slashcmd;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandCreator {

	private String name;
	private String description;
	private int type = -2173;
	private List<Option> options = new ArrayList<>();

	protected SlashCommandCreator() {}

	public static SlashCommandCreator create() {
		return new SlashCommandCreator();
	}


	public SlashCommandCreator setDescription(String _description) {
		description = _description;
		return this;
	}

	public SlashCommandCreator setName(String _name) {
		name = _name;
		return this;
	}

	public SlashCommandCreator setType(int _type) {
		type = _type;
		return this;
	}

	public Option createOption() {
		final Option option = new Option(this);
		options.add(option);
		return option;
	}

	public JsonObject json() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", name);
		if (type != -2173)
			jsonObject.addProperty("type", type);

		jsonObject.addProperty("description", description);

		JsonArray optionArray = new JsonArray();

		for (Option option : options) {
			JsonObject optionObject = new JsonObject();
			optionObject.addProperty("name", option.name);
			optionObject.addProperty("description", option.description);
			optionObject.addProperty("type", option.type);

			JsonArray choiceArray = new JsonArray();

			for (Option.Choice choice : option.choices) {
				JsonObject choiceObject = new JsonObject();
				choiceObject.addProperty("name", choice.name);
				choiceObject.addProperty("value", choice.value);
				choiceArray.add(choiceObject);
			}


			optionArray.add(optionObject);
		}

		jsonObject.add("options", optionArray);

		return jsonObject;
	}


	protected class Option {

		private SlashCommandCreator owner;
		private String name;
		private String description;
		private int type = -2173;
		private boolean required;
		private List<Choice> choices = new ArrayList<>();


		public Choice createChoice() {
			final Choice choice = new Choice(this);
			choices.add(choice);
			return choice;
		}

		public Option setDescription(String _description) {
			description = _description;
			return this;
		}

		public Option setName(String _name) {
			name = _name;
			return this;
		}

		public Option setOwner(SlashCommandCreator _owner) {
			owner = _owner;
			return this;
		}

		public Option setRequired(boolean _required) {
			required = _required;
			return this;
		}

		public Option setType(int _type) {
			type = _type;
			return this;
		}

		protected class Choice {
			private String name;
			private String value;
			private Option owner;

			public Choice setName(String _name) {
				name = _name;
				return this;
			}

			public Choice setValue(String _value) {
				value = _value;
				return this;
			}

			public Choice(Option _owner) {
				owner = _owner;
			}

			public Option end() {
				return owner;
			}
		}

		public Option(SlashCommandCreator _owner) {
			owner = _owner;
		}

		public SlashCommandCreator end() {
			return owner;
		}
	}


}
