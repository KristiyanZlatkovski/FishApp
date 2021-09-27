









      /**
       * Elements that make up the popup.
       */
      var container = document.getElementById('popup');
      var content = document.getElementById('popup-content');
	 var contentPic = document.getElementById('popup-content-img');
	  
	  
      var closer = document.getElementById('popup-closer');


      /**
       * Add a click handler to hide the popup.
       * @return {boolean} Don't follow the href.
       */
      closer.onclick = function() {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
      };


      /**
       * Create an overlay to anchor the popup to the map.
       */
      var overlay = new ol.Overlay({
        element: container,
        autoPan: true,
        autoPanAnimation: {
          duration: 250
        }
      });


      var features = [];
      for (var i=0; i<waters.length;i++) {
        features.push(coloredSvgMarker([waters[i].longtitude, waters[i].latitude], waters[i].name, i+1));
      }

      
      var vectorSource = new ol.source.Vector({ // VectorSource({
        features: features
      });

      var vectorLayer = new ol.layer.Vector({ // VectorLayer({
        source: vectorSource
      });
const bulgariaExtent = ol.proj.transformExtent([22.345023234, 41.238104147, 28.603526238, 44.228434539], 'EPSG:4326', 'EPSG:3857');
      var map = new ol.Map({
        layers: [
          new ol.layer.Tile({ // TileLayer({
           preload: Infinity,
            source: new ol.source.OSM()
          }), vectorLayer
        ],
        overlays: [overlay],
        target: 'map',
        view: new ol.View({
    center: ol.proj.fromLonLat([25.4833, 42.7250]),
    zoom: 6,
	maxZoom: 15,
	minZoom: 7,
extent: ol.extent.buffer(bulgariaExtent, 80000),
      showFullExtent: true,

   
  })
});

      // make the map's view to zoom and pan enough to display all the points
  

      /**
       * Add a click handler to the map to render the popup.
       */
      map.on('singleclick', function(evt) {
    	  var pixel = map.getPixelFromCoordinate(evt.coordinate);
    	  console.log(evt.coordinate);
        var name = map.forEachFeatureAtPixel(pixel, function(feature) {
          return feature.get('name');
      
        })
         var id = map.forEachFeatureAtPixel(pixel, function(feature) {
          return feature.get('id');
         })
        
        if (name) {
          
          var coordinate = evt.coordinate;
		
          content.innerHTML = name;
		contentPic.src=waters[id-1].path;
		
	
		
		
		 $.ajax({
			    type : "GET",
			    url : "/getFishesByWater",
			    data: {"name" : name},
			    

			    success: function (fragment) {       
					 $("#sidenavList").replaceWith(fragment);
				
					              
			    },
			    error: function (e) {
			        console.log(e);
			    }
			})  
		
		
		
		
		
		
		
		
		
		
		
		
		
		
          overlay.setPosition(coordinate);
        
		 container.style.display="block";
        } else {
          container.style.display="none";
        }
      });
      map.on('pointermove', function(evt) {
        map.getTargetElement().style.cursor = map.hasFeatureAtPixel(evt.pixel) ? 'pointer' : '';
      });


      function coloredSvgMarker(lonLat, name, place) {
      
         var feature = new ol.Feature({
           geometry: new ol.geom.Point(ol.proj.fromLonLat(lonLat)),
           name: name,
           id:place
           
         });
         

         feature.setStyle(
           new ol.style.Style({
             image: new ol.style.Icon({
               anchor: [0.5, 46],
          anchorXUnits: 'fraction',
          anchorYUnits: 'pixels',
          src: '../images/fishmark.png'
             })
           })
         );
         return feature;
      }