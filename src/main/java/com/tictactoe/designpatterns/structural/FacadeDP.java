package com.tictactoe.designpatterns.structural;

import com.sun.media.jfxmedia.control.VideoFormat;

public class FacadeDP {

	static class VideoFile{}

	static class OggCompressionCodec{}

	static class MPEG4CompressionCodec{}

	static class CodecFactory{}

	static class BitrateReader{}

	static class AudioMixer{}


	// We create a facade class to hide the framework's complexity
// behind a simple interface. It's a trade-off between
// functionality and simplicity.
	static class VideoConverter {
		String convert(String filename, String format) {
//			VideoFile file = new VideoFile();
//
//			sourceCodec =new CodecFactory.extract(file);
//			if(format.equals("mp4")) {
//				MPEG4CompressionCodec destinationCodec =new MPEG4CompressionCodec();
//			} else {
//				OggCompressionCodec destinationCodec =new OggCompressionCodec();
//			}
//
//			BitrateReader buffer = BitrateReader.read(filename,sourceCodec);
//			result = BitrateReader.convert(buffer,destinationCodec);
//			result = (new AudioMixer()).fix(result)
//			return new File(result);

			return "converted file bites 0110101010010";
		}

	}

	// Application classes don't depend on a billion classes
// provided by the complex framework. Also, if you decide to
// switch frameworks, you only need to rewrite the facade class.
	static class Application {

		static class Mp4 {

			String val;

			Mp4(String val) {
				this.val = val;
			}

			void save() {
				System.out.println("saved");
			}
		}

		public static void main(String[] args) {
			VideoConverter converter = new VideoConverter();

			Mp4 mp4 = new Mp4(converter.convert("funny-cats-video.ogg","mp4"));
			mp4.save();
		}

	}
}
