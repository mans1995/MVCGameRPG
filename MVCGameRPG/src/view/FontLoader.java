package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontLoader {
	
	public static Font load(String path, int size){
		Font font = null;
		try{
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, Thread.currentThread().getContextClassLoader().getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		return font;
	}

}
