import json, urllib
import webbrowser

import googlemaps
from datetime import datetime


GmapAPIKey = 'AIzaSyCt96TmgOpBKN3mwMe8Rlg-droJ58vLEYI'

gmaps = googlemaps.Client(key=GmapAPIKey)
geocode_result = gmaps.geocode('1600 Amphitheatre Parkway, Mountain View, CA')

reverse_geocode_result = gmaps.reverse_geocode((40.714224, -73.961452))

now = datetime.now()
directions_result = gmaps.directions("Sydney Town Hall",
                                     "Parramatta, NSW",
                                     mode="driving")

dump = json.dumps(directions_result)
stringthing = str(dump)


j = json.loads(stringthing)
startAddress = j[0]['legs'][0]['start_address']
endAddress = j[0]['legs'][0]['end_address']
print(j)
for leg in j[0]['legs'][0]['steps']:
    print(leg['html_instructions'])
