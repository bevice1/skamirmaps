
import SwiftUI

import class shared.MainViewFactory

struct ContentView: View {
    @State private var navPath = NavigationPath()
    var mainViewFactory: MainViewFactory {
        MainViewFactory()
    }
    var body: some View {
        UniversalVC {
            mainViewFactory.create()
        }
    }
}
