from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import os
import googlemaps

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://admin:123@localhost:3360/wanderweave'
app.config['GOOGLE_MAPS_API_KEY'] = os.environ.get('GOOGLE_MAPS_API_KEY')
db = SQLAlchemy(app)
gmaps = googlemaps.Client(key=app.config['GOOGLE_MAPS_API_KEY'])

class Activity(db.Model):
    __tablename__ = 'Activity'

    ActivityId = db.Column(db.BigInteger, primary_key=True, autoincrement=True)
    ActivityName = db.Column(db.String(255), nullable=False)
    Date = db.Column(db.Date, nullable=False)
    Time = db.Column(db.String(255), nullable=False)
    location = db.Column(db.String(255), nullable=False)
    latitude = db.Column(db.Float)
    longitude = db.Column(db.Float)
    created_at = db.Column(db.TIMESTAMP, nullable=False, default=datetime.utcnow)
    description = db.Column(db.String(255))
    TripId = db.Column(db.BigInteger, nullable=False)

# 데이터 추가 함수
def add_activity(activity_name, date, time, location, latitude, longitude, description, trip_id):
    new_activity = Activity(ActivityName=activity_name, Date=date, Time=time, location=location,
                            latitude=latitude, longitude=longitude, description=description, TripId=trip_id)
    db.session.add(new_activity)
    db.session.commit()

if __name__ == '__main__':
    app.run(debug=True)

