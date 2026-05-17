import math

class Driver:
    def __init__(self, driver_id, lat, lon):
        self.driver_id = driver_id
        self.lat = lat
        self.lon = lon
        self.is_available = True

class DispatchService:
    def __init__(self):
        self.drivers = []

    def add_driver(self, driver):
        self.drivers.append(driver)

    def _calculate_distance(self, lat1, lon1, lat2, lon2):
        # Спрощена формула розрахунку відстані (Евклідова відстань)
        return math.sqrt((lat1 - lat2)**2 + (lon1 - lon2)**2)

    def find_nearest_driver(self, passenger_lat, passenger_lon):
        nearest_driver = None
        min_distance = float('inf')

        for driver in self.drivers:
            if driver.is_available:
                distance = self._calculate_distance(
                    passenger_lat, passenger_lon, driver.lat, driver.lon
                )
                if distance < min_distance:
                    min_distance = distance
                    nearest_driver = driver

        if nearest_driver:
            nearest_driver.is_available = False # Водій прийняв замовлення
            return nearest_driver.driver_id
        return None

# Клієнтський код (симуляція запиту від API Gateway)
if __name__ == "__main__":
    dispatch = DispatchService()
    dispatch.add_driver(Driver(1, 50.001, 36.230))
    dispatch.add_driver(Driver(2, 50.005, 36.235))

    passenger_lat, passenger_lon = 50.002, 36.231
    matched_driver = dispatch.find_nearest_driver(passenger_lat, passenger_lon)
    print(f"Matched Driver ID: {matched_driver}")