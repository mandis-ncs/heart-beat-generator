# ❤️ Heart Beat Generator API - ECG Simulation

Welcome to **Heart Beat Generator API**! This project simulates **ECG (Electrocardiogram) measurements** in real-time, sending data via **WebSockets** to a backend system for anomaly detection. 🏥💓

This API generates **realistic ECG values**, including normal and anomalous readings, mimicking real-world heart activity.

## 🚀 Features

- ✅ **Realistic ECG Data Generation**: Simulates normal and anomalous heartbeats.
- 📡 **WebSocket Communication**: Sends ECG data to connected WebSocket clients.
- 🔄 **Configurable Interval**: Generates measurements at random intervals.
- 🛠️ **Hexagonal Architecture**: Clean and maintainable codebase.
- 🏗️ **Unit Tests**: Ensures stability and reliability.

## 🏗️ Project Structure

```
📂 heart-beat-generator
 ├── 📁 domain  # Core business logic
 │   ├── 📁 exceptions (MeasurementException)
 │   ├── 📁 models (EcgMeasurement)
 ├── 📁 service  # ECG data generation service
 │   ├── EcgSimulatorService.java
 ├── 📁 utils  # Utility classes
 │   ├── MeasurementScheduler.java
 ├── 📁 websocket  # WebSocket clients & handlers
 │   ├── CustomWebSocketClientHandler.java
 │   ├── MeasurementWebSocketClient.java
 │   ├── WebSocketClientConfig.java
 ├── 📁 resources  # Configuration files
 │   ├── application.yaml  # ⚠️ Contains configurations (for local testing only!)
 ├── 📁 test  # Unit tests
 ├── HeartBeatGeneratorApplication.java  # Main application entry point
 ├── README.md  # This file
```

## 📡 API Functionality

### **ECG Data Generation & Transmission**

- **Simulates heartbeats** at random intervals (10-100ms between measurements).
- **Sends data via WebSockets** to a backend listener.
- **20% chance of anomalies** to test irregularity detection.

### **WebSocket Communication**
- **WebSocket Endpoint:** `/ws/measurements`
- **Message Format (JSON Example):**
```json
{
  "value": 1.1,
  "timestamp": "2024-02-03T12:00:00Z",
  "deviceId": "HBM-12345"
}
```

## 🛠️ Running the Project

### **1️⃣ Prerequisites**
- 🏗️ **Java 17+**
- 🌐 **WebSocket Client** (Postman, WebSocket tester, etc.)

### **2️⃣ Clone the Repository**
```sh
git clone https://github.com/your-repo/heart-beat-generator.git
cd heart-beat-generator
```

### **3️⃣ Configure Application (Optional)** ⚠️
Modify `src/main/resources/application.yaml` to adjust configurations (e.g., WebSocket endpoint, timing settings).

### **4️⃣ Run the Application** 🚀
```sh
./mvnw spring-boot:run  # Linux/Mac
mvnw.cmd spring-boot:run  # Windows
```

## ✅ Unit Tests

The project includes **JUnit + Mockito** unit tests to validate ECG measurement generation and WebSocket communication.

### **Run all tests**
```sh
mvn test
```

### **Example Test Case (EcgSimulatorServiceTest)**
```java
@Test
void shouldGenerateValidMeasurement() {
    double measurement = ecgSimulatorService.generateMeasurement(false);
    assertTrue(measurement >= 0.8 && measurement <= 1.2);
}
```

## 🚀 Future Improvements

🔹 **✅ Dockerize the Application**
- Use **Docker Compose** to containerize the API.
- **Ensure WebSocket security & authentication**.

🔹 **📡 Enhance Data Variability**
- Improve ECG signal simulation for more realistic data.

🔹 **📈 Add REST API Support**
- Provide a REST endpoint for manual data retrieval.

---

## 🎯 Dependencies

This project depends on:
- **[HBM Backend API](https://github.com/mandis-ncs/hbm-backend)** (Processes ECG data and detects anomalies)
- **[WebSocket Clients](https://github.com/mandis-ncs/hbm-frontend)** (e.g., a monitoring dashboard frontend)

---

## 🎯 Contributors
👤 **Amanda Castro** | [Portfolio](https://mandis.framer.website/)

---

## ⚖️ License
This project is **MIT Licensed**. See [LICENSE](LICENSE) for details.

---

✅ **If you found this project useful, don't forget to ⭐ the repo!** 🚀🔥

