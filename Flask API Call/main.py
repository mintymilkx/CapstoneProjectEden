# Usage: python app.py

from flask import Flask, request, jsonify
from flask_restful import Resource, Api
from flask_cors import CORS
from unicodedata import name
import requests
import tensorflow as tf
from PIL import Image
from io import BytesIO
from urllib import response
import numpy as np
from urllib import response
import base64
import json

# Doing load model h5 from the model for plant classification
model_path = r'model_10Data'
model = tf.keras.models.load_model(model_path)

# Write the input image specification for the preprocessing
image_height = 224
image_width = 224
image_shape = (image_height, image_width) + (3,)

# Categories of various classes on classification to do GET the prediction classes
categories = ['17_Soleirolia soleirolii', '20_Gerbera Daisy', '26_Nymphaea', '29_Iris  plant',
              '30_Lavandula plant', 'Chinese Elm Bonsai', 'Haworthiopsis fasciata', 'Hypoestes phyllostachya', 
              'Kalanchoe thyrsiflora', 'Lithops']

# FLASK WEBB APP PROGRAM
app = Flask(__name__)
# initiate restfull object
api = Api(app)
# initiate object flask_cors
CORS(app)
# initiate empty variable untuk file
var_file = {}

class ResourceExm(Resource):
    def get(self):
        return var_file

    def post(self):
        # Doing preprocessing image data for prediction,
        # 'Keybase64' is the key that will be posted from the user
        image = np.array(tf.keras.utils.load_img(
            BytesIO(base64.b64decode(request.form['Keybase64'])), target_size=image_shape))
        image = np.expand_dims(image, axis=0)
        image = image/255.0
        image = image.astype('float16')

        # Doing make prediction on the image inputted in POST
        prediksi = model.predict(image)
        IndexPrediction = np.argmax(prediksi, axis=-1)

        var_file["predict1"] = categories[int(IndexPrediction)]
        # Making the message respond if the data has been entered,
        msg_resp = {"message": "Input image data has been entered"}
        return msg_resp

api.add_resource(ResourceExm, "/testing", methods=["GET", "POST"],)

if(__name__ == "__main__"):
    app.run(debug=True, port=5000)