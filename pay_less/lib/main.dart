// Copyright 2018 The Flutter team. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();

}

class _MyAppState extends State<MyApp> {
  GoogleMapController mapController;

  final LatLng _center = const LatLng(45.521563, -122.677433);

  void _onMapCreated(GoogleMapController controller) {
    mapController = controller;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Search'),
          actions: <Widget>[
            IconButton(
                icon: Icon(Icons.search),
                onPressed: () {
                  //showSearch(context: context, delegate: DataSearch());
                })
          ],
        ),
        body: GoogleMap(
          onMapCreated: _onMapCreated,
          initialCameraPosition: CameraPosition(
            target: _center,
            zoom: 11.0,
          ),
        ),
      ),
    );
  }
}

class DataSearch extends SearchDelegate<String> {
  final cities=[
    "Montreal",
   " NewYork",
    "Alepo",
    "Paris",
    "London",
    "Toronto",
  ];
  final recentCities= [
    "Toronto",
    "Montreal",
    "Ottawa",
  ];
  @override
  List<Widget> buildActions(BuildContext context) {
    // action for search bar
    return [IconButton(icon: Icon(Icons.clear), onPressed: () {})];
  }

  @override
  Widget buildLeading(BuildContext context) {
    // leading icon on the left of the bar
    return IconButton(
        icon: AnimatedIcon(
          icon: AnimatedIcons.menu_arrow,
          progress: transitionAnimation,
        ),
        onPressed: () {});
  }

  @override
  Widget buildResults(BuildContext context) {
    // show some results based on  selection
    return null;
  }

  @override
  Widget buildSuggestions(BuildContext context) {
    // show when someone searches for something
    final suggestionList = query.isEmpty ? recentCities: cities;
    return ListView.builder(
        itemBuilder: (context,index) => ListTile(
          leading: Icon(Icons.location_city),
          title: Text(suggestionList[index]),
        ),
        itemCount: suggestionList.length,
    );
  }
}
