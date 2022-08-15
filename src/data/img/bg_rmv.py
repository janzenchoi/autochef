"""
 Converts white pixels to transparent pixels
 Credit: https://stackoverflow.com/questions/765736/how-to-use-pil-to-make-all-white-pixels-transparent

"""

# Libraies
import os
from PIL import Image

# Get all pngs
all_png = [dir for dir in os.listdir("./") if dir.endswith(".png")]

# Iterature through png
for png in all_png:

    # Read data
    img = Image.open(png)
    img = img.convert("RGBA")
    pixels = img.getdata()

    # Make each pixel transparent
    new_pixels = []
    for pixel in pixels:
        if pixel[0] == 255 and pixel[1] == 255 and pixel[2] == 255:
            new_pixels.append((255, 255, 255, 0))
        else:
            new_pixels.append(pixel)

    # Save png as same name
    img.putdata(new_pixels)
    img.save(png, "PNG")

    # Indicate completion
    print("Converted {}".format(png))