import json
import psycopg2
import uuid

# Define the database connection details
db_config = {
    'dbname': 'intuit_db',
    'user': 'intuit_user',
    'password': 'intuitpassword',
    'host': 'localhost',
    'port': '5432'
}

# Connect to the PostgreSQL database
conn = psycopg2.connect(**db_config)
cursor = conn.cursor()



# Read JSON data
with open('photographer.json', 'r') as f:
    data = json.load(f)

# Insert data into the table
for item in data:
    cursor.execute('''
        INSERT INTO photographers (id, uid, password, first_name, last_name, username, email, avatar, gender,phone_number,social_insurance_number,date_of_birth,event_type,address,credit_card,subscription) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s,%s,%s,%s,%s,%s,%s,%s)
    ''', (item['id'], item['uid'], item['password'], item['first_name'], item['last_name'], item['username'], item['email'], item['avatar'], item['gender'],item['phone_number'],item['social_insurance_number'],item['date_of_birth'],json.dumps(item['event_type']),json.dumps(item['address']),json.dumps(item['credit_card']),json.dumps(item['subscription'])))
for item in data:
     for event in item['event_type']['type']:
       item_id = uuid.uuid4()
       cursor.execute('''
        INSERT INTO photographers_events (id, photographers_id, event_type) VALUES (%s, %s, %s)
        ''', (str(item_id),item['id'], event))
      
# Commit the transaction and close the connection
conn.commit()
conn.close()

print("Data has been inserted successfully.")
