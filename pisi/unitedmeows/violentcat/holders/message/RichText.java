package pisi.unitedmeows.violentcat.holders.message;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RichText extends SendMessage {

	protected List<Embed> embeds = new ArrayList<>();
	protected List<Component> components = new ArrayList<>();
	protected static Gson gson = new Gson();
	protected int componentType = 1;

	public void addEmbed(Embed embed) {
		embeds.add(embed);
	}

	public void addComponent(Component component) {
		components.add(component);
	}


	public RichText() {}


	public String json() {
		JsonObject json = new JsonObject();
		json.addProperty("content", content);
		json.addProperty("tts", tts);


		if (!embeds.isEmpty()) {
			JsonArray embedArray = new JsonArray();
			for (Embed embed : embeds) {
				JsonObject jsonEmbed = new JsonObject();
				jsonEmbed.addProperty("type", embed.type);
				jsonEmbed.addProperty("title", embed.title);
				jsonEmbed.addProperty("description", embed.description);
				int rgb = 65536 * embed.color.getRed() + 256 * embed.color.getGreen() + embed.color.getBlue();
				if (embed.color != null)
					jsonEmbed.addProperty("color", rgb);


				if (embed.image != null) {
					JsonObject imageJson = new JsonObject();
					imageJson.addProperty("url", embed.image.url);
					imageJson.addProperty("proxy_url", embed.image.proxyUrl);
					imageJson.addProperty("height", embed.image.height);
					imageJson.addProperty("width", embed.image.width);
					jsonEmbed.add("image", imageJson);
				}

				if (embed.thumbnail != null) {
					JsonObject thumbnailJson = new JsonObject();
					thumbnailJson.addProperty("url", embed.thumbnail.url);
					thumbnailJson.addProperty("proxy_url", embed.thumbnail.proxyUrl);
					thumbnailJson.addProperty("width", embed.thumbnail.width); // 10000 iq yüm
					thumbnailJson.addProperty("height", embed.thumbnail.height); // 10000 iq yüm
					jsonEmbed.add("thumbnail", thumbnailJson);
				}

				if (embed.author != null) {
					JsonObject authorJson = new JsonObject();
					authorJson.addProperty("name", embed.author.name);
					authorJson.addProperty("url", embed.author.url);
					authorJson.addProperty("icon_url", embed.author.iconUrl);
					jsonEmbed.add("author", authorJson);
				}

				if (embed.footer != null) {
					JsonObject footerJson = new JsonObject();
					footerJson.addProperty("text", embed.footer.text);
					footerJson.addProperty("icon_url", embed.footer.iconUrl);
					jsonEmbed.add("footer", footerJson);
				}



				jsonEmbed.addProperty("url", embed.url);
				embedArray.add(jsonEmbed);
			}
			json.add("embeds", embedArray);
		}

		if (!components.isEmpty()) {
			JsonArray mainComponents = new JsonArray();
			JsonArray componentArray = new JsonArray();
			for (Component component : components) {
				JsonObject componentJson = new JsonObject();
				componentJson.addProperty("style", component.style);
				componentJson.addProperty("label", component.label);
				componentJson.addProperty("url", component.url);
				componentJson.addProperty("disabled", component.disabled);
				componentJson.addProperty("type", component.type);


				if (component.emoji != null) {
					JsonObject emojiJson = new JsonObject();
					emojiJson.addProperty("id", component.emoji.id);
					emojiJson.addProperty("name", component.emoji.name);
					componentJson.add("emoji", emojiJson);
				}

				componentArray.add(componentJson);
			}//öyleyse intihar
			JsonObject componentsTop = new JsonObject();
			componentsTop.addProperty("type", componentType);
			componentsTop.add("components", componentArray);
			mainComponents.add(componentsTop);

			json.add("components", mainComponents);
		}

		return gson.toJson(json);
	}

	private String rgbToHex(int r, int g, int b)
	{
		Color hC;
		hC = new Color(r,g,b);
		String hex = Integer.toHexString(hC.getRGB() & 0xffffff);
		while(hex.length() < 6){
			hex = "0" + hex;
		}
		hex = "#0x" + hex;
		return hex;
	}

	public static class Image {
		protected String url;
		protected String proxyUrl;
		protected int width, height;

		public Image setHeight(int _height) {
			height = _height;
			return this;
		}

		public Image setProxyUrl(String _proxyUrl) {
			proxyUrl = _proxyUrl;
			return this;
		}

		public Image setUrl(String _url) {
			url = _url;
			return this;
		}

		public Image setWidth(int _width) {
			width = _width;
			return this;
		}
	}


	public static class Embed {
		public String type;
		protected String title;
		protected String description;
		protected Footer footer;
		protected String url;
		protected Image image;
		protected Color color;
		protected Author author;
		protected Image thumbnail;

		public Embed setAuthor(Author _author) {
			author = _author;
			return this;
		}

		public Embed setColor(Color _color) {
			color = _color;
			return this;
		}

		public Embed setDescription(String _description) {
			description = _description;
			return this;
		}

		public Embed setFooter(Footer _footer) {
			footer = _footer;
			return this;
		}

		public Embed setImage(Image _image) {
			image = _image;
			return this;
		}

		public Embed setThumbnail(Image _thumbnail) {
			thumbnail = _thumbnail;
			return this;
		}

		public Embed setTitle(String _title) {
			title = _title;
			return this;
		}

		public Embed setType(String _type) {
			type = _type;
			return this;
		}


		public Embed setUrl(String _url) {
			url = _url;
			return this;
		}
	}

	public static class Footer {
		protected String text;
		protected String iconUrl;

		public Footer setIconUrl(String _iconUrl) {
			iconUrl = _iconUrl;
			return this;
		}

		public Footer setText(String _text) {
			text = _text;
			return this;
		}
	}

	public static class Author {
		protected String name;
		protected String url;
		protected String iconUrl;

		public Author setIconUrl(String _iconUrl) {
			iconUrl = _iconUrl;
			return this;
		}

		public Author setName(String _name) {
			name = _name;
			return this;
		}

		public Author setUrl(String _url) {
			url = _url;
			return this;
		}
	}

	public static class Component {
		protected int style;
		protected String label;
		protected String url;
		protected boolean disabled;
		protected Emoji emoji;
		protected int type;

		public Component setDisabled(boolean _disabled) {
			disabled = _disabled;
			return this;
		}

		public Component setEmoji(Emoji _emoji) {
			emoji = _emoji;
			return this;
		}

		public Component setLabel(String _label) {
			label = _label;
			return this;
		}

		public Component setStyle(int _style) {
			style = _style;
			return this;
		}

		public Component setType(int _type) {
			type = _type;
			return this;
		}

		public Component setUrl(String _url) {
			url = _url;
			return this;
		}

	}

	public static class Emoji {
		protected int id;
		protected String name;

		public Emoji setId(int _id) {
			id = _id;
			return this;
		}

		public Emoji setName(String _name) {
			name = _name;
			return this;
		}
	}

	public static class Button {
		protected int style;
		protected String label;
		protected String customId;
		protected boolean disabled;
		protected int type;

		public Button setCustomId(String _customId) {
			customId = _customId;
			return this;
		}

		public Button setDisabled(boolean _disabled) {
			disabled = _disabled;
			return this;
		}

		public Button setLabel(String _label) {
			label = _label;
			return this;
		}

		public Button setStyle(int _style) {
			style = _style;
			return this;
		}

		public Button setType(int _type) {
			type = _type;
			return this;
		}
	}



}
